package com.pucmg.lab03.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pucmg.lab03.Models.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    
    
}
