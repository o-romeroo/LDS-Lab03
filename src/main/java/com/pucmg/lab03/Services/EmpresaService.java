package com.pucmg.lab03.Services;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pucmg.lab03.Models.Empresa;
import com.pucmg.lab03.Repositories.EmpresaRepository;
import com.pucmg.lab03.dto.EmpresaRequestDTO;

import jakarta.transaction.Transactional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public Empresa salvarEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Transactional
    public Empresa criarEmpresa(EmpresaRequestDTO empresaDto) throws IOException {
        Empresa empresa = new Empresa();
        empresa.setNome(empresaDto.getNome());
        empresa.setLogin(empresaDto.getLogin());
        empresa.setSenha(empresaDto.getSenha());
        empresa.setEmail(empresaDto.getEmail());
        if (empresaDto.getFotoPerfil() != null) {
            empresa.setFotoPerfil(empresaDto.getFotoPerfil().getBytes());
        } else {
            // Carrega a imagem padrão como array de bytes
            InputStream inputStream = getClass().getResourceAsStream("/static/images/default.png");
            byte[] defaultImageBytes = inputStream.readAllBytes();
            empresa.setFotoPerfil(defaultImageBytes);
        }
        return empresaRepository.save(empresa);
    }

    public Empresa buscarEmpresa(Long id) {
        return empresaRepository.findById(id).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
    }

}
