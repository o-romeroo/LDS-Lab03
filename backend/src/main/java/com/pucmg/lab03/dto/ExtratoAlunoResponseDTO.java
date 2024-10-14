package com.pucmg.lab03.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExtratoAlunoResponseDTO {
    
    private int montante;
    private String parteEnvolvida;
    private String detalhes;
    private LocalDate data;



}
