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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestorApp.payload.ContaBancariaDto;
import com.gestorApp.payload.ContaBancariaResponse;
import com.gestorApp.service.ContaBancariaService;
import com.gestorApp.utils.AppConstants;

@RestController
@RequestMapping("/api/conta-bancaria")
public class ContaBancariaController {
    
    @Autowired
    private ContaBancariaService contaBancariaService;

    @PostMapping
    public ResponseEntity<ContaBancariaDto> createContaBancaria(@RequestBody ContaBancariaDto contaBancariaDto){

        ContaBancariaDto response = contaBancariaService.createContaBancaria(contaBancariaDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ContaBancariaResponse> getAllContasBancarias(
        @RequestParam(required = false) String banco,
        @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
        @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        
        ContaBancariaResponse response = contaBancariaService.getAllContasBancarias(banco, pageNo, pageSize, sortBy, sortDir);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaBancariaDto> updateContaBancaria(@PathVariable(value = "id") long contaBancariaId, @RequestBody ContaBancariaDto contaBancariaDto){

        ContaBancariaDto response = contaBancariaService.updateContaBancaria(contaBancariaId, contaBancariaDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaBancariaDto> getContaBancariaById(@PathVariable(value = "id") long contaBancariaId){

        ContaBancariaDto response = contaBancariaService.getContaBancariaById(contaBancariaId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContaBancariaById(@PathVariable(value = "id") long contaBancariaId){

        contaBancariaService.deleteContaBancariaById(contaBancariaId);

        return new ResponseEntity<>("Conta bancária deleteda", HttpStatus.OK);
    }
}
