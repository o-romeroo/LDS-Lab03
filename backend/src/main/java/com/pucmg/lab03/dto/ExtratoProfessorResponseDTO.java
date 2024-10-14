package com.pucmg.lab03.dto;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExtratoProfessorResponseDTO{
    
    Long id;
    int montante;
    String destinatario;
    String motivo;
    LocalDate data;

}
