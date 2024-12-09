package com.pucmg.lab03.Controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pucmg.lab03.Models.Empresa;
import com.pucmg.lab03.Services.EmpresaService;
import com.pucmg.lab03.dto.EmpresaInstituicaoFotoResponseDTO;
import com.pucmg.lab03.dto.EmpresaRequestDTO;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @Operation(summary = "Cadastrar uma nova empresa", description = "Caso faça uma requisição sem dar upload de uma imagem, desmarcar a caixa \"<b>Send empty value</b>\".<br>\n" + //
    "Tentei de tudo pra tratar esse valor empty e não consegui.")
    @PostMapping(value = "/cadastrar", consumes = "multipart/form-data")
    public ResponseEntity<String> cadastrarEmpresa(@ModelAttribute EmpresaRequestDTO empresaDto) throws IOException {
            empresaService.criarEmpresa(empresaDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Empresa criada com sucesso!");
    }

    @Operation(summary = "Obter a foto de perfil de uma empresa para o header")
    @GetMapping("/header")
    public ResponseEntity<EmpresaInstituicaoFotoResponseDTO> buscarEmpresa(@RequestParam Long id) {
            Empresa empresa = empresaService.buscarEmpresa(id);
            EmpresaInstituicaoFotoResponseDTO empresaResponse = new EmpresaInstituicaoFotoResponseDTO(empresa.getFotoPerfil());
            return ResponseEntity.ok(empresaResponse);        
    }
    

}
