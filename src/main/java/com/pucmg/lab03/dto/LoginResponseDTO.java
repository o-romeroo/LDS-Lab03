package com.pucmg.lab03.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private Long id;
    private String userType;

    public LoginResponseDTO(Long id, String userType) {
        this.id = id;
        this.userType = userType;
    }

    
}

