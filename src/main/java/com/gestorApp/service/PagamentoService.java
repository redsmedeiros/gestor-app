package com.gestorApp.service;

import java.util.List;

import com.gestorApp.payload.PagamentoDto;

public interface PagamentoService {
    
    List<PagamentoDto> getAllPagamentos();
}
