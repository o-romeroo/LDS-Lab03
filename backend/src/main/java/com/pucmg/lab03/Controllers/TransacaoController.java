package com.pucmg.lab03.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

import com.pucmg.lab03.Models.Usuario;
import com.pucmg.lab03.Repositories.UsuarioRepository;
import com.pucmg.lab03.Services.TransacaoService;
import com.pucmg.lab03.dto.ExtratoProfessorResponseDTO;
import com.pucmg.lab03.dto.TransferenciaRequestDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
    

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @PostMapping("/transferencia")
    public ResponseEntity<String> transferirMoedas(@RequestBody TransferenciaRequestDTO transacaoRequest) {

        // Busca os usuários pelo ID
        Usuario remetente = usuarioRepository.findById(transacaoRequest.getRemetenteId())
                .orElseThrow(() -> new RuntimeException("Remetente não encontrado"));
        Usuario destinatario = usuarioRepository.findById(transacaoRequest.getDestinatarioId())
                .orElseThrow(() -> new RuntimeException("Destinatário não encontrado"));

        try {
            transacaoService.transferirMoedas(remetente, destinatario, transacaoRequest.getValor(), transacaoRequest.getMotivo());
            return ResponseEntity.ok("Transferência realizada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("professor/enviadas/{usuarioId}")
    public ResponseEntity<List<ExtratoProfessorResponseDTO>> buscarTransacoesEnviadas(@PathVariable Long usuarioId) {
        List<ExtratoProfessorResponseDTO> transacoesEnviadasDto = transacaoService.buscarTransacoesEnviadas(usuarioId).stream()
            .map(extrato -> new ExtratoProfessorResponseDTO(
                extrato.getId(),
                extrato.getMontante(),
                extrato.getDestinatario().getNome(),
                extrato.getMotivo(),
                extrato.getData()
            ))
            .collect(Collectors.toList());
    
        return ResponseEntity.ok(transacoesEnviadasDto);
    }
    


}
