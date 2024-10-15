package com.pucmg.lab03.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.pucmg.lab03.Models.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    
    
    
}
