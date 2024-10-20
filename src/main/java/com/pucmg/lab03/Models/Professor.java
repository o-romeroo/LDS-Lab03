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

@Table(name = "professor")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Professor extends Usuario{
    
    @Column
    private String cpf;

    @Column
    private String departamento;

    @Column
    private int saldoMoedas;

    @ManyToOne
    @JoinColumn(name = "instituicao_ensino_id")
    private InstituicaoEnsino instituicaoEnsino;

    

}
