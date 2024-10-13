package com.pucmg.lab03.Services;

import org.springframework.beans.factory.annotation.Autowired;

import com.pucmg.lab03.Models.Aluno;
import com.pucmg.lab03.Repositories.AlunoRepository;

public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public Aluno salvarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }
    
}
