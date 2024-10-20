package com.pucmg.lab03.dto;


import java.util.Base64;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaInstituicaoFotoResponseDTO {

    private String fotoPerfil;
    

    public EmpresaInstituicaoFotoResponseDTO(byte[] fotoPerfil) {
        this.fotoPerfil = "data:image/png;base64," + Base64.getEncoder().encodeToString(fotoPerfil);  // Converte para Base64
    }
    

    public EmpresaInstituicaoFotoResponseDTO() {
    }


}
