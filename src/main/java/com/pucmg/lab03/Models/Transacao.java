package com.pucmg.lab03.Models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "transacao")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {
    
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "remetente_id")
    private Usuario remetente;

    @ManyToOne
    @JoinColumn(name = "destinatario_id")
    private Usuario destinatario;

    @Column
    private String detalhes;

    @Column
    private int montante;

    @Column
    private LocalDate data;


    // @Column(columnDefinition = "BYTEA")
    @Lob
    private byte[] fotoRemetente;

    // @Column(columnDefinition = "BYTEA")
    @Lob
    private byte[] fotoDestinatario;

    // @Column(columnDefinition = "LONGBLOB")
    // private byte[] fotoRemetente;


    // @Column(columnDefinition = "LONGBLOB")
    // private byte[] fotoDestinatario;
}
