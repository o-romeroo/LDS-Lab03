package com.pucmg.lab03.dto;

import java.util.Base64;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VantagemResponseDTO {
    
    private String nome;

    private String imagem;

    private String descricao;

    private int preco;


    public VantagemResponseDTO(String nome, byte[] imagem, String descricao, int preco) {
        this.nome = nome;
        this.imagem = "data:image/png;base64," + Base64.getEncoder().encodeToString(imagem); 
        this.descricao = descricao;
        this.preco = preco;
    }

    public VantagemResponseDTO() {
    }


}
