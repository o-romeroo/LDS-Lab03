package com.pucmg.lab03.dto;

import java.time.LocalDate;

import java.util.Base64;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExtratoAlunoResponseDTO {
    
    private int montante;
    private String parteEnvolvida;
    private String detalhes;
    private LocalDate data;
    String fotoPerfil;


    public ExtratoAlunoResponseDTO() {
    }


    public ExtratoAlunoResponseDTO(int montante, String parteEnvolvida, String detalhes, LocalDate data, byte[] fotoPerfil) {
        this.montante = montante;
        this.parteEnvolvida = parteEnvolvida;
        this.detalhes = detalhes;
        this.data = data;
        this.fotoPerfil = "data:image/png;base64," + Base64.getEncoder().encodeToString(fotoPerfil);  // Converte para Base64
    }



}
