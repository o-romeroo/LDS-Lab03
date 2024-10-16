package com.pucmg.lab03.dto;

import java.time.LocalDate;

import java.util.Base64;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExtratoTodosAlunoResponseDTO {
    
    private int montante;
    private String parteEnvolvida;
    private String detalhes;
    private LocalDate data;
    String fotoPerfil;
    private String tipoTransacao;
    


    public ExtratoTodosAlunoResponseDTO() {
    }


    public ExtratoTodosAlunoResponseDTO(int montante, String parteEnvolvida, String detalhes, LocalDate data, byte[] fotoPerfil, String tipoTransacao) {
        this.montante = montante;
        this.parteEnvolvida = parteEnvolvida;
        this.detalhes = detalhes;
        this.data = data;
        this.fotoPerfil = "data:image/png;base64," + Base64.getEncoder().encodeToString(fotoPerfil);  // Converte para Base64
        this.tipoTransacao = tipoTransacao;
    }



}
