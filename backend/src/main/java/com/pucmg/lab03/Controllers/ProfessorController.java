package com.pucmg.lab03.Controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pucmg.lab03.Services.ProfessorService;
import com.pucmg.lab03.dto.ProfessorRequestDTO;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    ProfessorService professorService;
    
    @Operation(summary = "Cadastrar um novo professor")
    @PostMapping(value = "/cadastrar", consumes = "multipart/form-data")
    public ResponseEntity<String> cadastrarProfessor(@ModelAttribute ProfessorRequestDTO professorRequestDTO) {
        try {
            professorService.criarProfessor(professorRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Professor criado com sucesso!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar professor.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
