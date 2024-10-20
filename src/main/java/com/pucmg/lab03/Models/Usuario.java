package com.pucmg.lab03.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String login; 

    @Column
    private String senha;

    // @Column(columnDefinition = "LONGBLOB")
    // @Lob
    // private byte[] fotoPerfil;

    // @Column(columnDefinition = "BYTEA")
    @Lob
    private byte[] fotoPerfil;

    // Lista de transações em que o usuário é o remetente
    @OneToMany(mappedBy = "remetente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Transacao> transacoesEnviadas;

    // Lista de transações em que o usuário é o destinatário
    @OneToMany(mappedBy = "destinatario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Transacao> transacoesRecebidas;

}
