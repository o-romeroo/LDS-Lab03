package com.pucmg.lab03.Models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "instituicao_ensino")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstituicaoEnsino {
        
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @OneToMany(mappedBy = "instituicaoEnsino", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Vantagem> vantagens;


}
