package com.pucmg.lab03.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pucmg.lab03.Models.Vantagem;

public interface VantagemRepository extends JpaRepository<Vantagem, Long> {
    
    public List<Vantagem> findAll();

    public List<Vantagem> findByEmpresaId(Long empresaId);

    public List<Vantagem> findByInstituicaoEnsinoId(Long instituicaoEnsinoId);
}
