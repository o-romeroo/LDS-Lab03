package com.pucmg.lab03.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pucmg.lab03.Models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    public Usuario findByLogin(String login);

}
