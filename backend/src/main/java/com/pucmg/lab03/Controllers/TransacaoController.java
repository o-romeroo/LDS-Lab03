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
import com.pucmg.lab03.dto.ComprarVantagemRequestDTO;
import com.pucmg.lab03.dto.ExtratoAlunoResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Operation(description = "Transfere moedas de um professor para aluno.<br><br><b>remetenteId</b> é o <b>ID</b> do professor que está transferindo as moedas.<br>"
            +
            "<b>destinatarioId</b> é o <b>ID</b> do aluno que está recebendo as moedas.<br><b>valor</b> é a quantidade de moedas a ser transferida.<br><b>motivo</b>"
            + "é a justificativa da transferência.")
    @PostMapping("professor/transferencia")
    public ResponseEntity<String> transferirMoedas(@RequestBody TransferenciaRequestDTO transacaoRequest) {

        // Busca os usuários pelo ID
        Usuario remetente = usuarioRepository.findById(transacaoRequest.getRemetenteId())
                .orElseThrow(() -> new RuntimeException("Remetente não encontrado"));
        Usuario destinatario = usuarioRepository.findById(transacaoRequest.getDestinatarioId())
                .orElseThrow(() -> new RuntimeException("Destinatário não encontrado"));

        try {
            transacaoService.professorTransferirMoedas(remetente, destinatario, transacaoRequest.getValor(),
                    transacaoRequest.getMotivo());
            return ResponseEntity.ok("Transferência realizada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(description = "Retorna uma lista com todas as transações enviadas por um professor, {usuarioId} neste caso é o ID do professor")
    @GetMapping("professor/enviadas/{usuarioId}")
    public ResponseEntity<List<ExtratoProfessorResponseDTO>> buscarTransacoesEnviadasProfessor(
            @PathVariable Long usuarioId) {
        List<ExtratoProfessorResponseDTO> transacoesEnviadasDto = transacaoService.buscarTransacoesEnviadas(usuarioId)
                .stream()
                .map(extrato -> new ExtratoProfessorResponseDTO(
                        extrato.getFotoRemetente(),
                        extrato.getFotoDestinatario(),
                        extrato.getMontante(),
                        extrato.getDestinatario().getNome(),
                        extrato.getDetalhes(),
                        extrato.getData()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(transacoesEnviadasDto);
    }

    @Operation(description = "Retorna uma lista com todas as transações <b>recebidas</b> por um aluno (quando ele recebe moedas de um professor).<br>{usuarioId} neste caso é o ID do <b>aluno</b><br> este método faz parte da página Extrato do Aluno")
    @GetMapping("aluno/recebidas/{usuarioId}")
    public ResponseEntity<List<ExtratoAlunoResponseDTO>> buscarTransacoesRecebidasAluno(@PathVariable Long usuarioId) {
        List<ExtratoAlunoResponseDTO> transacoesRecebidasDto = transacaoService.buscarTransacoesRecebidas(usuarioId)
                .stream()
                .map(extrato -> new ExtratoAlunoResponseDTO(
                        extrato.getMontante(),
                        extrato.getDestinatario().getNome(),
                        extrato.getDetalhes(),
                        extrato.getData()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(transacoesRecebidasDto);
    }

    @Operation(description = "Retorna uma lista com todas as transações <b>enviadas</b> por um aluno (quando ele compra uma vantagem).<br>{usuarioId} neste caso é o ID do <b>aluno</b><br> este método faz parte da página Extrato do Aluno")
    @GetMapping("aluno/enviadas/{usuarioId}")
    public ResponseEntity<List<ExtratoAlunoResponseDTO>> buscarTransacoesEnviadasAluno(@PathVariable Long usuarioId) {
        List<ExtratoAlunoResponseDTO> transacoesRecebidasDto = transacaoService.buscarTransacoesRecebidas(usuarioId)
                .stream()
                .map(extrato -> new ExtratoAlunoResponseDTO(
                        extrato.getMontante(),
                        extrato.getRemetente().getNome(),
                        extrato.getDetalhes(),
                        extrato.getData()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(transacoesRecebidasDto);
    }

    @Operation(description = "Debita moedas de um aluno para comprar uma vantagem.")
    @PostMapping("aluno/compra")
    public ResponseEntity<String> comprarVantagem(@RequestBody ComprarVantagemRequestDTO request) {
        try {
            transacaoService.alunoComprarVantagem(request.getAlunoId(), request.getVantagemId());
            return ResponseEntity.ok("Vantagem comprada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
