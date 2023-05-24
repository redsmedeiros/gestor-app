package com.gestorApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestorApp.entity.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{
    
}
