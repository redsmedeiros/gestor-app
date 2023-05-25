package com.gestorApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestorApp.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
}
