package com.gestorApp.service;

import java.util.List;

import com.gestorApp.payload.PagamentoDto;

public interface PagamentoService {
    
    List<PagamentoDto> getAllPagamentos(String data, String formaPagamento, String status, int pageNo, int pageSize, String sortBy, String sortDir);

    PagamentoDto updatePagamento(long pagamentoId, PagamentoDto pagamentoDto);
}
