package com.gestorApp.service.Impl;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gestorApp.entity.Pagamento;
import com.gestorApp.exception.ResourceNotFoundException;
import com.gestorApp.payload.PagamentoDto;
import com.gestorApp.repository.PagamentoRepository;
import com.gestorApp.service.PagamentoService;

@Service
public class PagamentoServiceImpl implements PagamentoService{

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Override
    public List<PagamentoDto> getAllPagamentos(String data, String formaPagamento, String status, int pageNo, int pageSize, String sortBy, String sortDir) {

        Specification<Pagamento> spec = (root, query, criteriaBuilder) ->{

            List<Predicate> predicates = new ArrayList<>();


            if(formaPagamento != null){
                
                predicates.add(criteriaBuilder.equal(root.get("statusPagamento"), formaPagamento));
            }

            if(status != null){
                
                predicates.add(criteriaBuilder.equal(root.get("responsavel"), status));
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        
        Page<Pagamento> busca = pagamentoRepository.findAll(spec, pageable);

        List<Pagamento> listOfPagamentos = busca.getContent();

        List<PagamentoDto> content = busca.stream().map(pagamento -> mapToDto(pagamento)).collect(Collectors.toList());

        return content;
    }

    @Override
    public PagamentoDto updatePagamento(long pagamentoId, PagamentoDto pagamentoDto) {
        
        Pagamento pagamento = pagamentoRepository.findById(pagamentoId).orElseThrow(()-> new ResourceNotFoundException("pagamentoId", "id", pagamentoId));

        pagamento.setFormaPagamento(pagamentoDto.getFormaPagamento());

        Pagamento updated = pagamentoRepository.save(null);

        return mapToDto(updated);
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
