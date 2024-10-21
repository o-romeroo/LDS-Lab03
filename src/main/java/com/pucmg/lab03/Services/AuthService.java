package com.pucmg.lab03.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucmg.lab03.Models.Aluno;
import com.pucmg.lab03.Models.Empresa;
import com.pucmg.lab03.Models.Professor;
import com.pucmg.lab03.Models.Usuario;
import com.pucmg.lab03.Repositories.UsuarioRepository;
import com.pucmg.lab03.dto.LoginResponseDTO;
import jakarta.transaction.Transactional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public LoginResponseDTO login(String login, String senha) {
        Usuario usuario = usuarioRepository.findByLogin(login);
    
        if (usuario != null && usuario.getSenha().equals(senha)) {
            String userType = determinarTipoUsuario(usuario);  
            return new LoginResponseDTO(usuario.getId(), userType);
        } else {
            throw new RuntimeException("Credenciais inválidas");
        }
    }
    
    private String determinarTipoUsuario(Usuario usuario) {
        if (usuario instanceof Aluno) {
            return "Aluno";
        } else if (usuario instanceof Professor) {
            return "Professor";
        } else if (usuario instanceof Empresa) {
            return "Empresa";
        }
        else {
            return "InstituicaoEnsino";
        }
    }
    
}

