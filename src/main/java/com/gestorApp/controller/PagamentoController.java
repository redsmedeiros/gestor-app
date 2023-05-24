package com.gestorApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestorApp.payload.PagamentoDto;
import com.gestorApp.service.PagamentoService;

@RestController
@RequestMapping("/api/pagamento")
public class PagamentoController {
    
    @Autowired
    private PagamentoService pagamentoService;
    

    @GetMapping
    public List<PagamentoDto> getAllPagamentos(){

        List<PagamentoDto> response = pagamentoService.getAllPagamentos();

        return response;

    }
}
