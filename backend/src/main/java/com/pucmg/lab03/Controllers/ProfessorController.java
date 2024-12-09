package com.pucmg.lab03.Controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pucmg.lab03.Models.Professor;
import com.pucmg.lab03.Services.ProfessorService;
import com.pucmg.lab03.dto.ProfessorRequestDTO;
import com.pucmg.lab03.dto.ProfessorResponseHeaderDTO;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    ProfessorService professorService;
    
    @Operation(summary = "Cadastrar um novo professor", description = "Caso faça uma requisição sem dar upload de uma imagem, desmarcar a caixa \"<b>Send empty value</b>\".<br>" +
                        "Tentei de tudo pra tratar esse valor empty e não consegui.")
    @PostMapping(value = "/cadastrar", consumes = "multipart/form-data")
    public ResponseEntity<String> cadastrarProfessor(@ModelAttribute ProfessorRequestDTO professorRequestDTO) throws IOException {
            professorService.criarProfessor(professorRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Professor criado com sucesso!");
    }

    @Operation(summary = "Obter o saldo e a foto de perfil de um professor para o header")
    @GetMapping("/header")
    public ResponseEntity<ProfessorResponseHeaderDTO> buscarProfessor(@RequestParam Long id) {
        Professor professor = professorService.buscarProfessor(id);

        ProfessorResponseHeaderDTO ProfessorHeader = new ProfessorResponseHeaderDTO(professor.getSaldoMoedas(), professor.getFotoPerfil());

        return ResponseEntity.ok(ProfessorHeader);
}

}
