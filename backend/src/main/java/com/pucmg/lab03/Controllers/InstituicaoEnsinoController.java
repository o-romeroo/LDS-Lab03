package com.pucmg.lab03.Controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pucmg.lab03.Models.InstituicaoEnsino;
import com.pucmg.lab03.Services.InstituicaoEnsinoService;
import com.pucmg.lab03.dto.EmpresaInstituicaoFotoResponseDTO;
import com.pucmg.lab03.dto.InstituicaoEnsinoRequestDTO;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/instituicao-ensino")
public class InstituicaoEnsinoController {

    @Autowired
    private InstituicaoEnsinoService instituicaoEnsinoService;
    
    @Operation(summary = "Cadastrar uma nova instituição de ensino", description = "Caso faça uma requisição sem dar upload de uma imagem, desmarcar a caixa \"<b>Send empty value</b>\".<br>" + 
                        "Tentei de tudo pra tratar esse valor empty e não consegui.")
    @PostMapping(value = "/cadastrar", consumes = "multipart/form-data")
    public ResponseEntity<String>cadastrarInstituicaoEnsino(@ModelAttribute InstituicaoEnsinoRequestDTO instituicaoEnsinoDto) throws IOException {
            instituicaoEnsinoService.criarInstituicaoEnsino(instituicaoEnsinoDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Instituição de Ensino criada com sucesso!");
    }

    @Operation(summary = "Obter a foto de perfil de uma instituição de ensino para o header")
    @GetMapping("/header")
    public ResponseEntity<EmpresaInstituicaoFotoResponseDTO> buscarInstituicaoEnsino(@RequestParam Long id) {
            InstituicaoEnsino instituicaoEnsino = instituicaoEnsinoService.buscarInstituicaoEnsino(id);
            EmpresaInstituicaoFotoResponseDTO instituicaoEnsinoResponse = new EmpresaInstituicaoFotoResponseDTO(instituicaoEnsino.getFotoPerfil());
            return ResponseEntity.ok(instituicaoEnsinoResponse);
        
    }



}
