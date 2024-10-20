package com.pucmg.lab03.dto;
import java.time.LocalDate;
import java.util.Base64;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExtratoProfessorResponseDTO{
    
    private String fotoRemetente;
    private String fotoDestinatario;
    private int montante;
    private String destinatario;
    private String motivo;
    private LocalDate data;

    public ExtratoProfessorResponseDTO(byte[] fotoRemetente, byte[] fotoDestinatario, int montante, String destinatario, String motivo, LocalDate data) {
        this.fotoRemetente = "data:image/png;base64," + Base64.getEncoder().encodeToString(fotoRemetente);  // Converte para Base64
        this.fotoDestinatario = "data:image/png;base64," + Base64.getEncoder().encodeToString(fotoDestinatario);  // Converte para Base64
        this.montante = montante;
        this.destinatario = destinatario;
        this.motivo = motivo;
        this.data = data;
    }
    
    public ExtratoProfessorResponseDTO() {
    }

}
