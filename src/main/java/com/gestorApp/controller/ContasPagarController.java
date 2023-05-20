package com.gestorApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestorApp.payload.ContaPagaDto;
import com.gestorApp.service.ContaPagarService;

@RestController
@RequestMapping("/api/conta-pagar")
public class ContasPagarController {
    
    @Autowired
    private ContaPagarService contaPagarService;

    public ResponseEntity<ContaPagaDto> createConta(ContaPagaDto contaPagaDto){

        ContaPagaDto response = contaPagarService.createContaPagar(contaPagaDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }
}
