package com.pucmg.lab03.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucmg.lab03.Models.Vantagem;
import com.pucmg.lab03.Repositories.VantagemRepository;
import com.pucmg.lab03.dto.VantagemRequestDTO;
import java.io.IOException;


@Service
public class VantagemService {

    @Autowired
    private VantagemRepository vantagemRepository;

    public Vantagem buscarVantagem(Long id) {
        return vantagemRepository.findById(id).orElseThrow(() -> new RuntimeException("Vantagem não encontrada"));
    }

    public List<Vantagem> buscarTodasVantagens() {
        return vantagemRepository.findAll();
    }

    public Vantagem criarVantagem(VantagemRequestDTO vantagemDto) throws IOException {
        Vantagem vantagem = new Vantagem();
        vantagem.setDetalhes(vantagemDto.getDescricao());
        vantagem.setPreco(vantagemDto.getPreco());
        vantagem.setImagem(vantagemDto.getImagem().getBytes());
        return vantagemRepository.save(vantagem);

    }
    
}
