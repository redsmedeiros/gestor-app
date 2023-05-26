package com.gestorApp.service;

import com.gestorApp.payload.ProdutoDto;

public interface ProdutoService {
    
    ProdutoDto createProduto(ProdutoDto produtoDto);

    ProdutoDto getProdutoById(long produtoId);

    ProdutoDto updateProdutoById(long produtoId, ProdutoDto produtoDto);

    void deleteProdutoById(long produtoId);
}
