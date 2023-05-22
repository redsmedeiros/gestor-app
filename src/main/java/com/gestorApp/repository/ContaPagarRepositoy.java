package com.gestorApp.repository;

import com.gestorApp.entity.ContasPagar;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContaPagarRepositoy extends JpaRepository<ContasPagar, Long> {

    ContasPagar findByNumeroReferencia(String numeroReferencia);

    Page<ContasPagar> findAll(Specification<ContasPagar> spec, Pageable page); 

    List<ContasPagar> findByFornecedorId(long fornecedorId);
}
