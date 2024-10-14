package com.pucmg.lab03.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucmg.lab03.Models.Professor;
import com.pucmg.lab03.Repositories.ProfessorRepository;

import jakarta.transaction.Transactional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Transactional
    public Professor salvarProfessor(Professor professor) {
        return professorRepository.save(professor);
    }


}
