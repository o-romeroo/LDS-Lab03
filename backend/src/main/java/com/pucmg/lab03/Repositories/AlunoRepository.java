package com.pucmg.lab03.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pucmg.lab03.Models.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    
}
