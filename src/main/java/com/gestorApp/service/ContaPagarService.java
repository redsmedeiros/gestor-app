package com.gestorApp.service;

import java.util.List;

import com.gestorApp.payload.ContaPagaDto;
import com.gestorApp.payload.ContaPagarResponse;

public interface ContaPagarService {

    ContaPagaDto createContaPagar(ContaPagaDto contaPagaDto);

    List<ContaPagaDto> getAllContasPagar();

    ContaPagarResponse findAllContaPagar(String fornecedor, String statusPagamento, String responsavel, int pageNo, int pageSize, String sortBy, String sortDir);
    
}
