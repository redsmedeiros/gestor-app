package com.gestorApp.service.Impl;

import com.gestorApp.entity.ContasPagar;
import com.gestorApp.enums.StatusPagamento;
import com.gestorApp.payload.ContaPagaDto;
import com.gestorApp.repository.ContaPagarRepositoy;
import com.gestorApp.service.ContaPagarService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaPagarServiceImpl implements ContaPagarService {

    @Autowired
    private ContaPagarRepositoy contaPagarRepositoy;

    @Override
    public ContaPagaDto createContaPagar(ContaPagaDto contaPagaDto) {

        ContasPagar contaPagar = mapToEntity(contaPagaDto);

        ContasPagar notaExiste = contaPagarRepositoy.findByNumeroReferencia(contaPagaDto.getNumeroReferencia());

        if(notaExiste != null){
            throw new EntityNotFoundException("Nota não encontrada");
        }

        ContasPagar newContaPagar = contaPagarRepositoy.save(contaPagar);

        return mapToDto(newContaPagar);
    }

    private ContasPagar mapToEntity(ContaPagaDto contaPagaDto){

        ContasPagar contasPagar = new ContasPagar();

        contasPagar.setId(contaPagaDto.getId());
        contasPagar.setFornecedor(contaPagaDto.getFornecedor());
        contasPagar.setDataEmissao(contaPagaDto.getDataEmissao());
        contasPagar.setDataVencimento(contaPagaDto.getDataVencimento());
        contasPagar.setDescricao(contaPagaDto.getDescricao());
        contasPagar.setValorOriginal(contaPagaDto.getValorOriginal());
        contasPagar.setValorPago(contaPagaDto.getValorPago());
        contasPagar.setValorEmAberto(contaPagaDto.getValorEmAberto());
        contasPagar.setMetodoPagamento(contaPagaDto.getMetodoPagamento());
        contasPagar.setNumeroReferencia(contaPagaDto.getNumeroReferencia());
        contasPagar.setNotas(contaPagaDto.getNotas());
        contasPagar.setPrioridade(contaPagaDto.getPrioridade());
        contasPagar.setDepartamento(contaPagaDto.getDepartamento());
        contasPagar.setResponsavel(contaPagaDto.getResponsavel());
        contasPagar.setDataPagamento(contaPagaDto.getDataPagamento());

        StatusPagamento status = StatusPagamento.PENDENTE;
        contasPagar.setStatusPagamento(status.name());

        return contasPagar;
    }

    private ContaPagaDto mapToDto(ContasPagar contasPagar){

        ContaPagaDto contaPagaDto = new ContaPagaDto();

        contaPagaDto.setId(contasPagar.getId());
        contaPagaDto.setFornecedor(contasPagar.getFornecedor());
        contaPagaDto.setDataEmissao(contasPagar.getDataEmissao());
        contaPagaDto.setDataVencimento(contasPagar.getDataVencimento());
        contaPagaDto.setDescricao(contasPagar.getDescricao());
        contaPagaDto.setValorOriginal(contasPagar.getValorOriginal());
        contaPagaDto.setValorPago(contasPagar.getValorPago());
        contaPagaDto.setValorEmAberto(contasPagar.getValorEmAberto());
        contaPagaDto.setMetodoPagamento(contasPagar.getMetodoPagamento());
        contaPagaDto.setNumeroReferencia(contasPagar.getNumeroReferencia());
        contaPagaDto.setNotas(contasPagar.getNotas());
        contaPagaDto.setPrioridade(contasPagar.getPrioridade());
        contaPagaDto.setDepartamento(contasPagar.getDepartamento());
        contaPagaDto.setResponsavel(contasPagar.getResponsavel());
        contaPagaDto.setDataPagamento(contasPagar.getDataPagamento());
        contaPagaDto.setStatusPagamento(contasPagar.getStatusPagamento());

        return contaPagaDto;

    }
}
