package com.gestorApp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestorApp.entity.Produto;
import com.gestorApp.exception.ResourceNotFoundException;
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

    @Override
    public ProdutoDto getProdutoById(long produtoId) {

        Produto produto = produtoRepository.findById(produtoId).orElseThrow(()-> new ResourceNotFoundException("produtoId", "id", produtoId));
       
        return mapToDto(produto);
    }

    @Override
    public ProdutoDto updateProdutoById(long produtoId, ProdutoDto produtoDto) {

        Produto produto = produtoRepository.findById(produtoId).orElseThrow(()-> new ResourceNotFoundException("produtoId", "id", produtoId));

        if(produtoDto.getNome() != null){
            
            produto.setNome(produtoDto.getNome());
        }

        if(produtoDto.getPreco() != null){

            produto.setPreco(produtoDto.getPreco());
        }

        Produto updatedProduto = produtoRepository.save(produto);
       
        return mapToDto(updatedProduto);
    }

    @Override
    public void deleteProdutoById(long produtoId) {
       
        Produto produto = produtoRepository.findById(produtoId).orElseThrow(()-> new ResourceNotFoundException("produtoId", "id", produtoId));

        produtoRepository.deleteById(produto.getId());
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
