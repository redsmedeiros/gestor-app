package com.gestorApp.service;

import java.util.List;

import com.gestorApp.payload.ContaPagaDto;

public interface ContaPagarService {

    ContaPagaDto createContaPagar(ContaPagaDto contaPagaDto);

    List<ContaPagaDto> getAllContasPagar();

    
}
