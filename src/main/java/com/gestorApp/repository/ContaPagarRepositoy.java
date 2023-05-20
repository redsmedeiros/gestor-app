package com.gestorApp.repository;

import com.gestorApp.entity.ContasPagar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaPagarRepositoy extends JpaRepository<ContasPagar, Long> {

    ContasPagar findByNumeroReferencia(String numeroReferencia);
}
