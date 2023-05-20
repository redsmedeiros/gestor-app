package com.gestorApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestorApp.entity.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    
    Fornecedor findByCnpj(String cnpj);
}
