package com.gestorApp.service.Impl;

import com.gestorApp.entity.Fornecedor;
import com.gestorApp.payload.FornecedorDto;
import com.gestorApp.service.FornecedorService;

public class FornecedorServiceImpl implements FornecedorService {

    @Override
    public FornecedorDto createFornecedor(FornecedorDto fornecedorDto) {


        
        return null;
    }

    Fornecedor mapToEntity(FornecedorDto fornecedorDto){

        Fornecedor fornecedor = new Fornecedor();

        fornecedor.setId(fornecedorDto.getId());
        fornecedor.setNome(fornecedorDto.getNome());
        fornecedor.setEmail(fornecedorDto.getEmail());
        fornecedor.setCnpj(fornecedorDto.getCnpj());
        fornecedor.setEndereco(fornecedor.getEndereco());
        fornecedor.setTelefone(fornecedorDto.getTelefone());

        return fornecedor;
    }
    
}
