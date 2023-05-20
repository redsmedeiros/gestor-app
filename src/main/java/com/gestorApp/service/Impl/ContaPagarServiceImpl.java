package com.gestorApp.service.Impl;

import com.gestorApp.entity.ContasPagar;
import com.gestorApp.payload.ContaPagaDto;
import com.gestorApp.service.ContaPagarService;
import org.springframework.stereotype.Service;

@Service
public class ContaPagarServiceImpl implements ContaPagarService {

    @Override
    public ContaPagaDto createContaPagar(ContaPagaDto contaPagaDto) {
        return null;
    }

    private ContasPagar mapToEntity(ContaPagaDto contaPagaDto){
        
    }
}
