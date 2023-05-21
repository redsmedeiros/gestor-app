package com.gestorApp.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestorApp.payload.FornecedorDto;
import com.gestorApp.payload.FornecedorResponse;
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

    @PutMapping("/{id}")
    public ResponseEntity<FornecedorDto> updateFornecedorById(@PathVariable(value = "id") long fornecedorId, @RequestBody FornecedorDto fornecedorDto){

        FornecedorDto response = fornecedorService.updateFornecedorByid(fornecedorId, fornecedorDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFornecedorById(@PathVariable(value = "id") long fornecedorId){

        fornecedorService.deleteFornecedorById(fornecedorId);

        return new ResponseEntity<>("Fornecedor deletado com successo", HttpStatus.OK);
    }

    @GetMapping("/busca")
    public ResponseEntity<FornecedorResponse> buscarFornecedores(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cnpj,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "asc", required = false) String sortBy
            
    ) {

        FornecedorResponse response = fornecedorService.buscarFornecedores(nome, cnpj, pageNo, pageSize, sortBy);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
