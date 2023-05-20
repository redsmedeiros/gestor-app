package com.gestorApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestorApp.payload.ContaPagaDto;
import com.gestorApp.service.ContaPagarService;

import java.util.List;

@RestController
@RequestMapping("/api/conta-pagar")
public class ContasPagarController {
    
    @Autowired
    private ContaPagarService contaPagarService;

    @PostMapping
    public ResponseEntity<ContaPagaDto> createConta(@RequestBody ContaPagaDto contaPagaDto){

        ContaPagaDto response = contaPagarService.createContaPagar(contaPagaDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping
    public List<ContaPagaDto> getAllContasPagar(){

        List<ContaPagaDto> response = contaPagarService.getAllContasPagar();

        return response;
   }
}
