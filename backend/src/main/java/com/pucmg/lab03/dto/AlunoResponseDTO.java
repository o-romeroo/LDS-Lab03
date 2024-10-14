package com.pucmg.lab03.dto;


import java.util.Base64;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoResponseDTO {

    private Long id;
    private String fotoPerfil;
    private String nome;
    private String curso;

    public AlunoResponseDTO(Long id, byte[] fotoPerfil, String nome, String curso) {
        this.id = id;
        this.fotoPerfil = "data:image/png;base64," + Base64.getEncoder().encodeToString(fotoPerfil);  // Converte para Base64
        this.nome = nome;
        this.curso = curso;
    }
    

    public AlunoResponseDTO() {
    }


}
