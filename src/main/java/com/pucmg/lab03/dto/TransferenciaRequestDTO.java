package com.pucmg.lab03.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaRequestDTO {

    private Long remetenteId;
    private Long destinatarioId;
    private int valor;
    private String motivo;


}

