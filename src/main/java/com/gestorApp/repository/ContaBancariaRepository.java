package com.gestorApp.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gestorApp.entity.ContaBancaria;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long>{

    Page<ContaBancaria> findAll(Specification<ContaBancaria> spec, Pageable page);

    ContaBancaria findByNumeroConta(String numeroConta);

    ContaBancaria findByBanco(String banco);
}
