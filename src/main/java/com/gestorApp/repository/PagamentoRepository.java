package com.gestorApp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gestorApp.entity.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{

    Page<Pagamento> findAll(Specification<Pagamento> spec, Pageable page);
    
}
