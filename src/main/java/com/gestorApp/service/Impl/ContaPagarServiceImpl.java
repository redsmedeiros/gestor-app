package com.gestorApp.service.Impl;

import com.gestorApp.entity.ContasPagar;
import com.gestorApp.entity.Fornecedor;
import com.gestorApp.enums.StatusPagamento;
import com.gestorApp.payload.ContaPagaDto;
import com.gestorApp.payload.ContaPagarResponse;
import com.gestorApp.repository.ContaPagarRepositoy;
import com.gestorApp.service.ContaPagarService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Predicates;
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

    @Override
    public List<ContaPagaDto> getAllContasPagar() {
        
        List<ContasPagar> contasPagar = contaPagarRepositoy.findAll();

        List<ContaPagaDto> response = contasPagar.stream().map(contaPagar -> mapToDto(contaPagar)).collect(Collectors.toList());
        
        return response;
    }

    @Override
    public ContaPagarResponse findAllContaPagar(String fornecedor, String statusPagamento, String responsavel,
            int pageNo, int pageSize, String sortBy, String sortDir) {

                Specification<Fornecedor> spec = (root, query, criteriaBuilder) ->{

                    List<Predicate> predicates = new ArrayList<>();

                    if(fornecedor != null){
                        
                        predicates.add(criteriaBuilder.equal(root.get("fornecedor"), fornecedor));
                    }

                    if(statusPagamento != null){
                        
                        predicates.add(criteriaBuilder.equal(root.get("statusPagamento"), statusPagamento));
                    }

                    if(responsavel != null){
                        
                        predicates.add(criteriaBuilder.equal(root.get("responsavel"), responsavel));
                    }


                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                };

                Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

                Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

                Page<ContasPagar> busca = contaPagarRepositoy.findAll(spec, pageable);

                List<ContasPagar> listOfContas = busca.getContent();

                List<ContaPagaDto> content = listOfContas.stream().map(contaPagar -> mapToDto(contaPagar)).collect(Collectors.toList());

                ContaPagarResponse contaPagarResponse = new ContaPagarResponse();

                contaPagarResponse.setContent(content);
                contaPagarResponse.setPageNo(busca.getNumber());
                contaPagarResponse.setPageSize(busca.getSize());
                contaPagarResponse.setTotalElements(busca.getTotalElements());
                contaPagarResponse.setTotalPages(busca.getTotalPages());
                contaPagarResponse.setLast(busca.isLast());
       
        
        return contaPagarResponse;
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
