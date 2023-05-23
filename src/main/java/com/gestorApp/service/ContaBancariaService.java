package com.gestorApp.service;

import com.gestorApp.payload.ContaBancariaDto;
import com.gestorApp.payload.ContaBancariaResponse;

public interface ContaBancariaService {

    ContaBancariaDto createContaBancaria(ContaBancariaDto contaBancariaDto);

    ContaBancariaResponse getAllContasBancarias(String banco, int pageNo, int pageSize, String sortBy, String sorDir);

    ContaBancariaDto updateContaBancaria(long contaBancariaId, ContaBancariaDto contaBancariaDto);  
    
    ContaBancariaDto getContaBancariaById(long contaBancariaId);

    void deleteContaBancariaById(long contaBancariaId);
}
