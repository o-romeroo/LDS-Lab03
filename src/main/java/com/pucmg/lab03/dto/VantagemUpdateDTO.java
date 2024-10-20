package com.pucmg.lab03.dto;


import org.springframework.web.multipart.MultipartFile;

import io.micrometer.common.lang.Nullable;
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
    
    @Nullable
    private MultipartFile imagem;

}
