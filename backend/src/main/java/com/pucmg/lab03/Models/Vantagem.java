package com.pucmg.lab03.Models;




import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vantagem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vantagem {
    
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String detalhes;

    @Column
    private int preco;

    // @Column(columnDefinition = "LONGBLOB")
    // @Lob
    // @Nullable
    // private byte[] imagem;

    @Column(columnDefinition = "BYTEA")
    @Lob
    private byte[] imagem;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = true)
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "instituicao_ensino_id", nullable = true)
    private InstituicaoEnsino instituicaoEnsino;

}
