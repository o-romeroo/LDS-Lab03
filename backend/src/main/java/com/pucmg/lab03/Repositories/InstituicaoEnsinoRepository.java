package com.pucmg.lab03.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pucmg.lab03.Models.InstituicaoEnsino;

public interface InstituicaoEnsinoRepository extends JpaRepository<InstituicaoEnsino, Long> {
    InstituicaoEnsino findByAluno(Long id);
}
