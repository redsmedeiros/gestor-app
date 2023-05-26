package com.gestorApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestorApp.entity.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    
}
