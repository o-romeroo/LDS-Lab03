package com.pucmg.lab03.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "aluno")
@Entity
@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
public class Aluno extends Usuario{
    
    @Column
    private String email;

    @Column
    private String rg;

    @Column
    private String endereco;

    @ManyToOne
    @JoinColumn(name = "instituicao_ensino_id")
    private InstituicaoEnsino instituicaoEnsino;

    @Column
    private String curso;

    @Column
    private int saldoMoedas;

    @Column
    private int totalMoedasRecebidas;
}
