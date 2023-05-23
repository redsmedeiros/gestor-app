package com.gestorApp.service;

import java.util.List;

import com.gestorApp.payload.ContaBancariaDto;
import com.gestorApp.payload.ContaBancariaResponse;

public interface ContaBancariaService {

    ContaBancariaDto createContaBancaria(ContaBancariaDto contaBancariaDto);

    ContaBancariaResponse getAllContasBancarias(String banco, int pageNo, int pageSize, String sortBy, String sorDir);
    
}
