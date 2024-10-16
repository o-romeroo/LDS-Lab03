package com.pucmg.lab03.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VantagemUpdateDTO {
    
    
    private String descricao;

    private int preco;

    private MultipartFile imagem;

    private Long usuarioId;
    

}
