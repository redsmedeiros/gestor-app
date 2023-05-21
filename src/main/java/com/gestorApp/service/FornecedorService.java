package com.gestorApp.service;

import java.util.List;

import com.gestorApp.payload.FornecedorDto;
import com.gestorApp.payload.FornecedorResponse;

public interface FornecedorService {
    
    FornecedorDto createFornecedor(FornecedorDto fornecedorDto);

    List<FornecedorDto> getAllFornecedores();

    FornecedorDto getFornecedorById(long fornecedorId);

    FornecedorDto updateFornecedorByid(long fornecedorId, FornecedorDto fornecedorDto);

    void deleteFornecedorById(long fornecedorId);
    
    FornecedorResponse buscarFornecedores(String nome, String cnpj, int pageNo, int pageSize, String sortBy);
}
