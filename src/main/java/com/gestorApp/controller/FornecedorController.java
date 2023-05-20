package com.gestorApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestorApp.payload.FornecedorDto;
import com.gestorApp.service.FornecedorService;

@RestController
@RequestMapping("/api/fornecedor")
public class FornecedorController {
    
    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<FornecedorDto> createFornecedor(@RequestBody FornecedorDto fornecedorDto){

        FornecedorDto response = fornecedorService.createFornecedor(fornecedorDto);
        
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public List<FornecedorDto> getAllFornecedores(){
        
        List<FornecedorDto> response = fornecedorService.getAllFornecedores();

        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorDto> getFornecedorById(@PathVariable(value = "id") long fornecedorId){

        FornecedorDto response = fornecedorService.getFornecedorById(fornecedorId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
