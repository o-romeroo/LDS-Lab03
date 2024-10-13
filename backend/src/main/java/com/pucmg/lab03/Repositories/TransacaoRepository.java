package com.pucmg.lab03.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pucmg.lab03.Models.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    
}
