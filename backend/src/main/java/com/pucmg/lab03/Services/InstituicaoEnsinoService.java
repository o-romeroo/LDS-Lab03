package com.pucmg.lab03.Services;

import java.io.IOException;

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
    public InstituicaoEnsino criarInstituicaoEnsino(InstituicaoEnsinoRequestDTO instituicaoEnsinoDto) throws IOException {
        InstituicaoEnsino instituicaoEnsino = new InstituicaoEnsino();
        instituicaoEnsino.setNome(instituicaoEnsinoDto.getNome());
        instituicaoEnsino.setLogin(instituicaoEnsinoDto.getLogin());
        instituicaoEnsino.setSenha(instituicaoEnsinoDto.getSenha());
        instituicaoEnsino.setFotoPerfil(instituicaoEnsinoDto.getFotoPerfil().getBytes());
        return instituicaoEnsinoRepository.save(instituicaoEnsino);
    }

}
