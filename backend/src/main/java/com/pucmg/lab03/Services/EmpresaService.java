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



// Sugestão: Padronize as mensagens de log para incluir o nome do método e a ação realizada.
// Isso facilita o rastreamento em ambientes de produção e aumenta a clareza das operações logadas.

// Sugestão: Adicione validações nos parâmetros recebidos (ex: verificar se IDs ou objetos são nulos).
// Isso ajuda a evitar erros em tempo de execução e garante que o método lide apenas com dados válidos.

// Sugestão: Centralize o tratamento de exceções para simplificar a manutenção.
// Considere o uso de exceções customizadas para diferenciar falhas de negócio de falhas técnicas.

// Sugestão: Adicione comentários explicativos nos métodos principais para facilitar o entendimento do código,
// especialmente em pontos onde a lógica é mais complexa ou onde há maior risco de erro.

// Sugestão: Caso algum método esteja muito grande, divida em métodos menores com responsabilidades específicas.
// Isso melhora a legibilidade e facilita o teste de unidade e a manutenção do código.
