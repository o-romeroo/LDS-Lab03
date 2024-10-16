package com.pucmg.lab03.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucmg.lab03.Models.InstituicaoEnsino;
import com.pucmg.lab03.Models.Professor;
import com.pucmg.lab03.Repositories.InstituicaoEnsinoRepository;
import com.pucmg.lab03.Repositories.ProfessorRepository;
import com.pucmg.lab03.dto.ProfessorRequestDTO;
import java.io.IOException;

import jakarta.transaction.Transactional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private InstituicaoEnsinoRepository instituicaoEnsinoRepository;

    @Transactional
    public Professor salvarProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    @Transactional
    public Professor criarProfessor(ProfessorRequestDTO professorRequestDTO) throws IOException {
        Professor professor = new Professor();
        professor.setNome(professorRequestDTO.getNome());
        professor.setLogin(professorRequestDTO.getLogin());
        professor.setSenha(professorRequestDTO.getSenha());
        // Converte MultipartFile para byte[] para a fotoPerfil
        professor.setFotoPerfil(professorRequestDTO.getFotoPerfil().getBytes());
        professor.setCpf(professorRequestDTO.getCpf());
        professor.setDepartamento(professorRequestDTO.getDepartamento());
        professor.setSaldoMoedas(1000);
        
        InstituicaoEnsino instituicao = instituicaoEnsinoRepository.findById(professorRequestDTO.getInstituicaoEnsinoId())
            .orElseThrow(() -> new RuntimeException("Instituição de ensino não encontrada"));
        professor.setInstituicaoEnsino(instituicao);
        salvarProfessor(professor);
    
        return professor;
    }

    

}
