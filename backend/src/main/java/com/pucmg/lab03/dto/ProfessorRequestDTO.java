package com.pucmg.lab03.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorRequestDTO {
    
    private String nome;
    private String login;
    private String senha;
    private MultipartFile fotoPerfil;
    private String cpf;
    private String departamento;
    private Long instituicaoEnsinoId; // ID da instituição de ensino


}
