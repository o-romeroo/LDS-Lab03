package com.pucmg.lab03.Services;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucmg.lab03.Models.InstituicaoEnsino;
import com.pucmg.lab03.Repositories.InstituicaoEnsinoRepository;
import com.pucmg.lab03.dto.InstituicaoEnsinoRequestDTO;

import jakarta.transaction.Transactional;

@Service
public class InstituicaoEnsinoService {

    @Autowired
    private InstituicaoEnsinoRepository instituicaoEnsinoRepository;

    @Transactional
    public InstituicaoEnsino criarInstituicaoEnsino(InstituicaoEnsinoRequestDTO instituicaoEnsinoDto)
            throws IOException {
        InstituicaoEnsino instituicaoEnsino = new InstituicaoEnsino();
        instituicaoEnsino.setNome(instituicaoEnsinoDto.getNome());
        instituicaoEnsino.setLogin(instituicaoEnsinoDto.getLogin());
        instituicaoEnsino.setSenha(instituicaoEnsinoDto.getSenha());
        instituicaoEnsino.setEmail(instituicaoEnsinoDto.getEmail());
        if (instituicaoEnsinoDto.getFotoPerfil() != null) {
            instituicaoEnsino.setFotoPerfil(instituicaoEnsinoDto.getFotoPerfil().getBytes());
        } else {
            // Carrega a imagem padrão como array de bytes
            InputStream inputStream = getClass().getResourceAsStream("/static/images/default.png");
            byte[] defaultImageBytes = inputStream.readAllBytes();
            instituicaoEnsino.setFotoPerfil(defaultImageBytes);
        }
        return instituicaoEnsinoRepository.save(instituicaoEnsino);
    }

}
