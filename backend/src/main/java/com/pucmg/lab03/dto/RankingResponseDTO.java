package com.pucmg.lab03.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RankingResponseDTO {
    
    int posicaoRanking;
    String nome;
    String curso;
    int totalMoedasRecebidas;

}
