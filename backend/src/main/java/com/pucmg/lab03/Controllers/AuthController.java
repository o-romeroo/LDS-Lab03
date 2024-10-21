package com.pucmg.lab03.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pucmg.lab03.Services.AuthService;
import com.pucmg.lab03.dto.LoginRequestDTO;
import com.pucmg.lab03.dto.LoginResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {
     @Autowired
    private AuthService authService;  // Injeta o serviço de autenticação

    @Operation(summary = "Realizar login")
    @GetMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            LoginResponseDTO response = authService.login(loginRequest.getLogin(), loginRequest.getSenha());
            return ResponseEntity.ok(response);  
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(null);  
        }
    }
}
