package com.pucmg.lab03.Controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pucmg.lab03.Services.InstituicaoEnsinoService;
import com.pucmg.lab03.dto.InstituicaoEnsinoRequestDTO;

@RestController
@RequestMapping("/instituicao-ensino")
public class InstituicaoEnsinoController {

    @Autowired
    private InstituicaoEnsinoService instituicaoEnsinoService;

    public ResponseEntity<String>cadastrarInstituicaoEnsino(@ModelAttribute InstituicaoEnsinoRequestDTO instituicaoEnsinoDto) {
        try {
            instituicaoEnsinoService.criarInstituicaoEnsino(instituicaoEnsinoDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Instituição de Ensino criada com sucesso!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar instituição de ensino.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
