package com.pucmg.lab03.dto;


import java.util.Base64;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoResponseHeaderDTO {

    private int saldoMoedas;
    private String fotoPerfil;
    

    public AlunoResponseHeaderDTO(int saldoMoedas, byte[] fotoPerfil) {
        this.saldoMoedas = saldoMoedas;
        this.fotoPerfil = "data:image/png;base64," + Base64.getEncoder().encodeToString(fotoPerfil);  // Converte para Base64
    }
    

    public AlunoResponseHeaderDTO() {
    }


}
