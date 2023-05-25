package com.gestorApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestorApp.payload.ProdutoDto;
import com.gestorApp.service.ProdutoService;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoDto> createProduto(@RequestBody ProdutoDto produtoDto){

        ProdutoDto response = produtoService.createProduto(produtoDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
}
