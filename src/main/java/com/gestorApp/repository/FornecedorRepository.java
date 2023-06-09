package com.gestorApp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gestorApp.entity.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    
    Fornecedor findByCnpj(String cnpj);

    List<Fornecedor> findAll(Specification<Fornecedor> spec);

    Page<Fornecedor> findAll(Specification<Fornecedor> spec, Pageable pageable);
}
