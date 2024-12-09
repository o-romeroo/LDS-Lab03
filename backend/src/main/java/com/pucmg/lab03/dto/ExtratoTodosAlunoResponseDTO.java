package com.pucmg.lab03.dto;

import java.time.LocalDate;

import com.pucmg.lab03.utils.ImageUtils;

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
        this.fotoPerfil = ImageUtils.convertToBase64(fotoPerfil);  // Converte para Base64
        this.tipoTransacao = tipoTransacao;
    }



}
