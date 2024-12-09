package com.pucmg.lab03.dto;
import java.time.LocalDate;

import com.pucmg.lab03.utils.ImageUtils;

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
        this.fotoRemetente = ImageUtils.convertToBase64(fotoRemetente);  // Converte para Base64
        this.fotoDestinatario = ImageUtils.convertToBase64(fotoDestinatario);  // Converte para Base64
        this.montante = montante;
        this.destinatario = destinatario;
        this.motivo = motivo;
        this.data = data;
    }
    
    public ExtratoProfessorResponseDTO() {
    }

}
