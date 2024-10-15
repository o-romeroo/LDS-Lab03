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

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    
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
