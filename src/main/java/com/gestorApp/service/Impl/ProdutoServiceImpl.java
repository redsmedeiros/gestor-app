package com.gestorApp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestorApp.entity.Produto;
import com.gestorApp.payload.ProdutoDto;
import com.gestorApp.repository.ProdutoRepository;
import com.gestorApp.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public ProdutoDto createProduto(ProdutoDto produtoDto) {

        Produto produto = mapToEntity(produtoDto);

        Produto newProduto = produtoRepository.save(produto);
        
        return mapToDto(newProduto);
    }

    private Produto mapToEntity(ProdutoDto produtoDto){

        Produto produto = Produto.builder()
            .id(produtoDto.getId())
            .nome(produtoDto.getNome())
            .preco(produtoDto.getPreco())
            .build();

        return produto;
    }

    private ProdutoDto mapToDto(Produto produto){

        ProdutoDto produtoDto = ProdutoDto.builder()
            .id(produto.getId())
            .nome(produto.getNome())
            .preco(produto.getPreco())
            .build();
        
        return produtoDto;
    }
    
}
