package com.pucmg.lab03.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucmg.lab03.Models.Aluno;
import com.pucmg.lab03.Models.InstituicaoEnsino;
import com.pucmg.lab03.Repositories.AlunoRepository;
import com.pucmg.lab03.Repositories.InstituicaoEnsinoRepository;
import com.pucmg.lab03.dto.AlunoRequestDTO;


import jakarta.transaction.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class AlunoService {

    @Autowired
    AlunoRepository alunoRepository;

    public Aluno salvarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno buscarAluno(Long id) {
        return alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public List<Aluno> buscarTodosAlunos() {
        return alunoRepository.findAll();
    }

    public List<Aluno> melhoresAlunos(){
        return alunoRepository.findAllByOrderByTotalMoedasRecebidasDesc();

    }

    public Aluno buscarAlunoPorEmail(String email) {
        return alunoRepository.findByEmail(email);
    }

    @Autowired
    private InstituicaoEnsinoRepository instituicaoEnsinoRepository;

    @Transactional
    public Aluno criarAluno(AlunoRequestDTO alunoDto) throws IOException {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoDto.getNome());
        aluno.setLogin(alunoDto.getLogin());
        aluno.setSenha(alunoDto.getSenha());
        aluno.setEmail(alunoDto.getEmail());
        aluno.setRg(alunoDto.getRg());
        aluno.setEndereco(alunoDto.getEndereco());
        aluno.setCurso(alunoDto.getCurso());

        // Converte MultipartFile para byte[] para a fotoPerfil
        aluno.setFotoPerfil(alunoDto.getFotoPerfil().getBytes());

        // Busca e associa a Instituição de Ensino
        InstituicaoEnsino instituicao = instituicaoEnsinoRepository.findById(alunoDto.getInstituicaoEnsinoId())
            .orElseThrow(() -> new RuntimeException("Instituição de ensino não encontrada"));
        aluno.setInstituicaoEnsino(instituicao);

        return salvarAluno(aluno);
    }
    
}
