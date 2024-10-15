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
public class InstituicaoEnsinoRequestDTO {
    
    String nome;
    String login;
    String senha;
    MultipartFile fotoPerfil;


}
