package com.pucmg.lab03.Controllers;

import java.io.IOException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pucmg.lab03.Services.VantagemService;
import com.pucmg.lab03.dto.VantagemRequestDTO;
import com.pucmg.lab03.dto.VantagemResponseDTO;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/vantagem")
public class VantagemController {
    
    @Autowired
    private VantagemService vantagemService;

    @Operation(summary = "Cadastrar uma nova vantagem",
               description = "usuarioId é o id do usuário que está cadastrando a vantagem, podendo ser uma InstituicaoEnsino ou Empresa.")
    @PostMapping(value = "/cadastrar", consumes = "multipart/form-data")
    public ResponseEntity<String> cadastrarVantagem(@ModelAttribute VantagemRequestDTO vantagemDto) {
        try {
            vantagemService.criarVantagem(vantagemDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Vantagem criada com sucesso!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar vantagem.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Lista com todas as vantagens")
    @GetMapping("/todas")
    public ResponseEntity<List<VantagemResponseDTO>> buscarTodasVantagens() {
        List<VantagemResponseDTO> vantagensDto = vantagemService.buscarTodasVantagens().stream()
                .map(vantagem -> new VantagemResponseDTO(
                        vantagem.getEmpresa().equals(null) ? vantagem.getInstituicaoEnsino().getNome()  
                            : vantagem.getEmpresa().getNome(),
                        vantagem.getImagem(),
                        vantagem.getDetalhes(),
                        vantagem.getPreco()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(vantagensDto);
    }

}
