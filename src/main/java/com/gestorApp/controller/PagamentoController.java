package com.gestorApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestorApp.payload.ConfirmaPagamentoDto;
import com.gestorApp.payload.PagamentoDto;
import com.gestorApp.service.PagamentoService;
import com.gestorApp.utils.AppConstants;

@RestController
@RequestMapping("/api/pagamento")
public class PagamentoController {
    
    @Autowired
    private PagamentoService pagamentoService;
    

    @GetMapping
    public List<PagamentoDto> getAllPagamentos(
        @RequestParam(required = false) String data,
        @RequestParam(required = false) String formaPagamento,
        @RequestParam(required = false) String status,
        @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){

        List<PagamentoDto> response = pagamentoService.getAllPagamentos(data, formaPagamento, status, pageNo, pageSize, sortDir, sortBy);

        return response;

    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoDto> updatePagamento(@PathVariable(value = "id") long pagamentoId, @RequestBody PagamentoDto pagamentoDto){

        PagamentoDto response = pagamentoService.updatePagamento(pagamentoId, pagamentoDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/confirma/{id}")
    public ResponseEntity<PagamentoDto> confirmaPagamento(@PathVariable(value = "id") long pagamentoId, @RequestBody ConfirmaPagamentoDto confirmaPagamentoDto){

        PagamentoDto response = pagamentoService.confirmarPagamento(pagamentoId, confirmaPagamentoDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
