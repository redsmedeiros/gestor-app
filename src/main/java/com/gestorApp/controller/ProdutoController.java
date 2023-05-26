package com.gestorApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> getProdutoById(@PathVariable(name = "id") long produtoId){

        ProdutoDto response = produtoService.getProdutoById(produtoId);

        return new ResponseEntity<ProdutoDto>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> updateProduto(@PathVariable(name = "id") long produtoId, @RequestBody ProdutoDto produtoDto){

        ProdutoDto response = produtoService.updateProdutoById(produtoId, produtoDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") long produtoId){

        produtoService.deleteProdutoById(produtoId);

        return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
    }
    
}
