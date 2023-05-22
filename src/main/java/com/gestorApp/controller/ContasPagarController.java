package com.gestorApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestorApp.payload.ContaPagaDto;
import com.gestorApp.payload.ContaPagarResponse;
import com.gestorApp.service.ContaPagarService;
import com.gestorApp.utils.AppConstants;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ContasPagarController {
    
    @Autowired
    private ContaPagarService contaPagarService;

    @PostMapping("/fornecedor/{fornecedorId}/contas-pagar")
    public ResponseEntity<ContaPagaDto> createConta(@PathVariable(value = "fornecedorId") long fornecedorId , @RequestBody ContaPagaDto contaPagaDto){

        ContaPagaDto response = contaPagarService.createContaPagar(fornecedorId ,contaPagaDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/fornecedor/{fornecedorId}/contas-pagar")
    public List<ContaPagaDto> getAllContasByFornecedorId(
     @PathVariable(value = "fornecedorId") long fornecedorId,
     @RequestParam(required = false) String statusPagamento,
     @RequestParam(required = false) String dataVencimento
     ){

          List<ContaPagaDto> response = contaPagarService.findAllContaByFornecedorid(fornecedorId, statusPagamento, dataVencimento);

          return response;
    }


   @GetMapping
   public ResponseEntity<ContaPagarResponse> findAllContas(
        @RequestParam(required = false) String fornecedor,
        @RequestParam(required = false) String statusPagamento,
        @RequestParam(required = false) String responsavel,
        @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
   ){

        ContaPagarResponse response = contaPagarService.findAllContaPagar(fornecedor, statusPagamento, responsavel, pageNo, pageSize, sortBy, sortDir);

        return new ResponseEntity<>(response, HttpStatus.OK);
   }

   @GetMapping("/fornecedor/{fornecedorId}/contas-pagar/{contaPagarId}")
   public ResponseEntity<ContaPagaDto> getContaById(@PathVariable(value = "fornecedorId") long fornecedorId, @PathVariable(value = "contaPagarId") long contaPagarId){

          ContaPagaDto response = contaPagarService.findContaById(fornecedorId, contaPagarId);

          return new ResponseEntity<>(response, HttpStatus.OK);
   }
}
