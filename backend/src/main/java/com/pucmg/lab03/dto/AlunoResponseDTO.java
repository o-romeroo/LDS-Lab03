package com.pucmg.lab03.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoResponseDTO {

    private Long id;
    private String nome;
    private String curso;

    
}
