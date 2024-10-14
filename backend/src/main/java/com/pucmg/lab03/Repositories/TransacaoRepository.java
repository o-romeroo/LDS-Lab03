package com.pucmg.lab03.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.pucmg.lab03.Models.Transacao;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    
    // Busca todas as transações enviadas por um usuário específico
    List<Transacao> findByRemetenteId(Long remetenteId);

    // Busca todas as transações recebidas por um usuário específico
    List<Transacao> findByDestinatarioId(Long destinatarioId);
}
