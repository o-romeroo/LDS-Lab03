package com.pucmg.lab03.dto;


import com.pucmg.lab03.utils.ImageUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoResponseHeaderDTO {

    private int saldoMoedas;
    private String fotoPerfil;
    

    public AlunoResponseHeaderDTO(int saldoMoedas, byte[] fotoPerfil) {
        this.saldoMoedas = saldoMoedas;
        this.fotoPerfil = ImageUtils.convertToBase64(fotoPerfil);  // Converte para Base64
    }
    

    public AlunoResponseHeaderDTO() {
    }


}
