package com.gestorApp.service;

import java.util.List;

import com.gestorApp.payload.FornecedorDto;

public interface FornecedorService {
    
    FornecedorDto createFornecedor(FornecedorDto fornecedorDto);

    List<FornecedorDto> getAllFornecedores();

    FornecedorDto getFornecedorById(long fornecedorId);

    FornecedorDto updateFornecedorByid(long fornecedorId, FornecedorDto fornecedorDto);

    void deleteFornecedorById(long fornecedorId);

    List<FornecedorDto> buscarFornecedores(String nome, String cnpj);
}
