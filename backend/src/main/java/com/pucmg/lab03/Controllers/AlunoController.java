package com.pucmg.lab03.Controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;

import com.pucmg.lab03.dto.AlunoRequestDTO;

import com.pucmg.lab03.Services.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/all")
    public ResponseEntity<List<AlunoRequestDTO>> buscarTodosAlunos() {
        List<AlunoRequestDTO> alunosDto = alunoService.buscarTodosAlunos().stream()
            .map(aluno -> new AlunoRequestDTO(aluno.getId(), aluno.getNome(), aluno.getCurso()))
            .collect(Collectors.toList());

        return ResponseEntity.ok(alunosDto);
    }
}
