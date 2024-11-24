package com.pucmg.lab03.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pucmg.lab03.Models.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    
    
}


// Sugestão: Verifique se os nomes dos métodos de query estão em conformidade com as convenções padrão do Spring Data,
// para que a equipe consiga identificar facilmente os filtros e a funcionalidade de cada método.

// Sugestão: Considere verificar se os métodos de consulta podem ser suficientemente genéricos para evitar duplicação de código,
// facilitando a reutilização em diferentes cenários e a manutenção.

// Sugestão: Garanta que os métodos de busca retornem valores consistentes, como listas vazias em vez de null.
// Isso evita exceções de NullPointerException e torna o código mais seguro.
