package com.pucmg.lab03.dto;


import com.pucmg.lab03.utils.ImageUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaInstituicaoFotoResponseDTO {

    private String fotoPerfil;
    

    public EmpresaInstituicaoFotoResponseDTO(byte[] fotoPerfil) {
        this.fotoPerfil = ImageUtils.convertToBase64(fotoPerfil);  // Converte para Base64
    }
    

    public EmpresaInstituicaoFotoResponseDTO() {
    }


}
