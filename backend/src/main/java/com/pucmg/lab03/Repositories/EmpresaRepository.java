package com.pucmg.lab03.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.pucmg.lab03.Models.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    
    
    
}



// Sugestão: Nos métodos de query customizados, verifique se o nome dos métodos está claro e descritivo
// o suficiente para indicar a ação realizada e os filtros aplicados.

// Sugestão: Para métodos que retornam um objeto único, como findById, é interessante o uso de Optional.
// Isso evita problemas com valores nulos e deixa o código mais seguro contra exceções de NullPointer.

// Sugestão: Em queries customizadas, adicione anotações explicativas documentando a funcionalidade.
// Isso ajuda na compreensão da lógica de consulta e facilita futuras manutenções ou ajustes nas queries.
