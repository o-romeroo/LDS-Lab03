package com.pucmg.lab03.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.pucmg.lab03.Models.Empresa;
import com.pucmg.lab03.Models.InstituicaoEnsino;
import com.pucmg.lab03.Models.Usuario;
import com.pucmg.lab03.Models.Vantagem;
import com.pucmg.lab03.Repositories.UsuarioRepository;
import com.pucmg.lab03.Repositories.VantagemRepository;
import com.pucmg.lab03.dto.VantagemRequestDTO;
import com.pucmg.lab03.dto.VantagemUpdateDTO;

import jakarta.transaction.Transactional;

import java.io.IOException;
import java.io.InputStream;

@Service
public class VantagemService {

    @Autowired
    private VantagemRepository vantagemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Vantagem buscarVantagem(Long id) {
        return vantagemRepository.findById(id).orElseThrow(() -> new RuntimeException("Vantagem não encontrada"));
    }

    public List<Vantagem> buscarTodasVantagens() {
        return vantagemRepository.findAll();
    }

    @Transactional
    public Vantagem criarVantagem(VantagemRequestDTO vantagemDto) throws IOException {
        Vantagem vantagem = new Vantagem();
        vantagem.setDetalhes(vantagemDto.getDescricao());
        vantagem.setPreco(vantagemDto.getPreco());

        // Verifica se a imagem é válida e se contém dados
        if (vantagemDto.getImagem() != null) {
            vantagem.setImagem(vantagemDto.getImagem().getBytes());
        } else {
            // Carrega a imagem padrão como array de bytes
            InputStream inputStream = getClass().getResourceAsStream("/static/images/semvantagem.png");
            byte[] defaultImageBytes = inputStream.readAllBytes();
            vantagem.setImagem(defaultImageBytes);
        }

        Usuario usuario = usuarioRepository.findById(vantagemDto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (usuario instanceof Empresa) {
            vantagem.setEmpresa((Empresa) usuario);
        } else {
            vantagem.setInstituicaoEnsino((InstituicaoEnsino) usuario);
        }

        return vantagemRepository.save(vantagem);
    }

    @Transactional
    public void deletarVantagem(Long id) {
        Vantagem vantagem = vantagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vantagem não encontrada"));
        vantagemRepository.delete(vantagem);
    }

    @Transactional
    public Vantagem editarVantagem(Long vantagemId, VantagemUpdateDTO vantagemDto) throws IOException {
        Vantagem vantagem = buscarVantagem(vantagemId);
        vantagem.setDetalhes(vantagemDto.getDescricao());
        vantagem.setPreco(vantagemDto.getPreco());

        if (vantagemDto.getImagem() != null) {
            vantagem.setImagem(vantagemDto.getImagem().getBytes());
        } else {
            vantagem.setImagem(vantagem.getImagem());
        }

        return vantagemRepository.save(vantagem);
    }

}
