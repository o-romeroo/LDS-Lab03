package com.pucmg.lab03.dto;

import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoRequestDTO {

    private String nome;
    private String login;
    private String senha;
    private MultipartFile fotoPerfil;
    private String email;
    private String rg;
    private String endereco;
    private Long instituicaoEnsinoId; // ID da instituição de ensino
    private String curso;
}

