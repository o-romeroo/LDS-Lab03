package com.pucmg.lab03.Controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pucmg.lab03.dto.AlunoRequestDTO;
import com.pucmg.lab03.dto.AlunoResponseDTO;

import io.swagger.v3.oas.annotations.Operation;

import com.pucmg.lab03.Services.AlunoService;



@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @Operation(
    summary = "Buscar todos os alunos cadastrados",
    description = "<b>Descrição:</b><br>" +
                  "Retorna uma lista com todos os alunos cadastrados na tela inicial do Professor.<br><br>" +
                  "<b>Exemplo de Resposta:</b><br>" +
                  "<pre>" +
                  "[<br>" +
                  "    {<br>" +
                  "        \"id\": 1,<br>" +
                  "        \"fotoPerfil\": \"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAA...\",<br>" +
                  "        \"nome\": \"João Silva\",<br>" +
                  "        \"curso\": \"Engenharia\"<br>" +
                  "    },<br>" +
                  "    {<br>" +
                  "        \"id\": 2,<br>" +
                  "        \"fotoPerfil\": \"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAA...\",<br>" +
                  "        \"nome\": \"Maria Oliveira\",<br>" +
                  "        \"curso\": \"Medicina\"<br>" +
                  "    }<br>" +
                  "]" +
                  "</pre>" +
                  "<br>" +

                  "<b>Exemplo de Uso da imagem Front-End:</b><br>" +
                  "<pre>" +
                  "fetch('http://localhost:8080/alunos/all')<br>" +
                  "    .then(response => response.json())<br>" +
                  "    .then(alunos => {<br>" +
                  "        alunos.forEach(aluno => {<br>" +
                  "            const alunoDiv = document.createElement('div');<br>" +
                  "            const img = document.createElement('img');<br>" +
                  "            `img.src = aluno.fotoPerfil;` // Usa a string Base64 diretamente no src da imagem<br>" +
                  "</pre>"
)   
    @GetMapping("/todos")
    public ResponseEntity<List<AlunoResponseDTO>> buscarTodosAlunos() {
        List<AlunoResponseDTO> alunosDto = alunoService.buscarTodosAlunos().stream()
                .map(aluno -> new AlunoResponseDTO(
                        aluno.getId(),
                        aluno.getFotoPerfil(), // Obtém a imagem como byte[]
                        aluno.getNome(),
                        aluno.getCurso()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(alunosDto);
    }

    @Operation(summary = "Cadastrar um novo aluno", description = "Caso faça uma requisição sem dar upload de uma imagem, desmarcar a caixa \"<b>Send empty value</b>\".<br>" + 
                "Tentei de tudo pra tratar esse valor empty e não consegui.")
    @PostMapping(value = "/cadastrar", consumes = "multipart/form-data")
    public ResponseEntity<String> criarAluno(@ModelAttribute AlunoRequestDTO alunoDto) {
        try {
            alunoService.criarAluno(alunoDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Aluno criado com sucesso!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar aluno.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    

}