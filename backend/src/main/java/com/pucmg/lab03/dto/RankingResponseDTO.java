package com.pucmg.lab03.dto;


import com.pucmg.lab03.utils.ImageUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankingResponseDTO {
    
    int posicaoRanking;
    String fotoPerfil;
    String nome;
    String curso;
    int totalMoedasRecebidas;


    public RankingResponseDTO() {
    }


    public RankingResponseDTO(int posicaoRanking, byte[] fotoPerfil, String nome, String curso, int totalMoedasRecebidas) {
        this.posicaoRanking = posicaoRanking;
        this.fotoPerfil = ImageUtils.convertToBase64(fotoPerfil);
        this.nome = nome;
        this.curso = curso;
        this.totalMoedasRecebidas = totalMoedasRecebidas;
    }

}
