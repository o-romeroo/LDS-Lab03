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

import jakarta.transaction.Transactional;

import java.io.IOException;


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
        vantagem.setImagem(vantagemDto.getImagem().getBytes());
        Usuario usuario = usuarioRepository.findById(vantagemDto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
                if (usuario instanceof Empresa) {
                    vantagem.setEmpresa((Empresa) usuario);
                } else {
                    vantagem.setInstituicaoEnsino((InstituicaoEnsino)usuario);
                }
        return vantagemRepository.save(vantagem);

    }
    
}
