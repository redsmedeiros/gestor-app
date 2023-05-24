package com.gestorApp.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestorApp.entity.Pagamento;
import com.gestorApp.payload.PagamentoDto;
import com.gestorApp.repository.PagamentoRepository;
import com.gestorApp.service.PagamentoService;

@Service
public class PagamentoServiceImpl implements PagamentoService{

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Override
    public List<PagamentoDto> getAllPagamentos() {
        
        List<Pagamento> pagamentos = pagamentoRepository.findAll();

        List<PagamentoDto> listOfPagamentos = pagamentos.stream().map(pagamento -> mapToDto(pagamento)).collect(Collectors.toList());

        return listOfPagamentos;
    }

    private PagamentoDto mapToDto(Pagamento pagamento) {
        PagamentoDto pagamentoDto = new PagamentoDto();
        pagamentoDto.setId(pagamento.getId());
        pagamentoDto.setData(pagamento.getData());
        pagamentoDto.setFormaPagamento(pagamento.getFormaPagamento());
        pagamentoDto.setStatus(pagamento.getStatus());
        pagamentoDto.setValor(pagamento.getValor());
        return pagamentoDto;
    }
    
}
