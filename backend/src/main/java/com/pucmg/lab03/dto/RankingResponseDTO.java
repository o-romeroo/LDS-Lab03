package com.pucmg.lab03.dto;



import java.util.Base64;

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
        this.fotoPerfil = "data:image/png;base64," + Base64.getEncoder().encodeToString(fotoPerfil);
        this.nome = nome;
        this.curso = curso;
        this.totalMoedasRecebidas = totalMoedasRecebidas;
    }

}
