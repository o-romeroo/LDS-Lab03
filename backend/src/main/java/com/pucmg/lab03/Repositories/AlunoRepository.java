package com.pucmg.lab03.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pucmg.lab03.Models.Aluno;
import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    
    public List<Aluno> findAll();

    List<Aluno> findAllByOrderByTotalMoedasRecebidasDesc();

    Aluno findByEmail(String email);
}
