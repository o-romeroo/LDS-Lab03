package com.pucmg.lab03.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucmg.lab03.Models.InstituicaoEnsino;
import com.pucmg.lab03.Models.Professor;
import com.pucmg.lab03.Repositories.InstituicaoEnsinoRepository;
import com.pucmg.lab03.Repositories.ProfessorRepository;
import com.pucmg.lab03.dto.ProfessorRequestDTO;
import java.io.IOException;
import java.io.InputStream;

import jakarta.transaction.Transactional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private InstituicaoEnsinoRepository instituicaoEnsinoRepository;

    @Transactional
    public Professor salvarProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor buscarProfessor(Long id) {
        return professorRepository.findById(id).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    }
    
    @Transactional
    public Professor criarProfessor(ProfessorRequestDTO professorRequestDTO) throws IOException {
        Professor professor = new Professor();
        professor.setNome(professorRequestDTO.getNome());
        professor.setLogin(professorRequestDTO.getLogin());
        professor.setSenha(professorRequestDTO.getSenha());
        // Converte MultipartFile para byte[] para a fotoPerfil
        if (professorRequestDTO.getFotoPerfil() != null) {
            professor.setFotoPerfil(professorRequestDTO.getFotoPerfil().getBytes());
        } else {
            // Carrega a imagem padrão como array de bytes
            InputStream inputStream = getClass().getResourceAsStream("/static/images/default.png");
            byte[] defaultImageBytes = inputStream.readAllBytes();
            professor.setFotoPerfil(defaultImageBytes);
        }
        professor.setCpf(professorRequestDTO.getCpf());
        professor.setDepartamento(professorRequestDTO.getDepartamento());
        professor.setSaldoMoedas(1000);

        InstituicaoEnsino instituicao = instituicaoEnsinoRepository
                .findById(professorRequestDTO.getInstituicaoEnsinoId())
                .orElseThrow(() -> new RuntimeException("Instituição de ensino não encontrada"));
        professor.setInstituicaoEnsino(instituicao);
        salvarProfessor(professor);

        return professor;
    }

}


// Sugestão: Considere usar injeção de dependências via construtor ao invés de injeção direta.
// Isso facilita a realização de testes unitários e a criação de mocks, aumentando a testabilidade do código.

// Sugestão: Considere o uso de DTOs para transferir dados entre camadas,
// especialmente para evitar a exposição direta das entidades e facilitar a manutenção da API.

// Sugestão: Centralize mensagens de erro em uma classe de constantes ou propriedades, caso sejam reutilizadas.
// Isso garante consistência e facilita possíveis alterações futuras em mensagens de erro.

// Sugestão: Adicione documentação aos métodos públicos para descrever sua funcionalidade, parâmetros e valores de retorno.
// Isso ajuda outros desenvolvedores a entenderem rapidamente o propósito de cada método.

// Sugestão: Se houver condicionais complexas, considere refatorá-las em métodos utilitários para melhorar a legibilidade do código.
