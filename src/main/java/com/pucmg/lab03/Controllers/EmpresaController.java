package com.pucmg.lab03.Controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pucmg.lab03.Services.EmpresaService;
import com.pucmg.lab03.dto.EmpresaRequestDTO;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Operation(summary = "Cadastrar uma nova empresa", description = "Caso faça uma requisição sem dar upload de uma imagem, desmarcar a caixa \"<b>Send empty value</b>\".<br>\n" + //
    "Tentei de tudo pra tratar esse valor empty e não consegui.")
    @PostMapping(value = "/cadastrar", consumes = "multipart/form-data")
    public ResponseEntity<String> cadastrarEmpresa(@ModelAttribute EmpresaRequestDTO empresaDto) {
        try {
            empresaService.criarEmpresa(empresaDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Empresa criada com sucesso!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar empresa.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}
