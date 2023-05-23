package com.gestorApp.service.Impl;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;
import java.util.stream.Collectors;

import com.gestorApp.entity.ContasPagar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gestorApp.entity.ContaBancaria;
import com.gestorApp.payload.ContaBancariaDto;
import com.gestorApp.payload.ContaBancariaResponse;
import com.gestorApp.repository.ContaBancariaRepository;
import com.gestorApp.service.ContaBancariaService;

@Service
public class ContaBancariaServiceImpl implements ContaBancariaService{

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @Override
    public ContaBancariaDto createContaBancaria(ContaBancariaDto contaBancariaDto) {

        ContaBancaria contaBancaria = mapToEntity(contaBancariaDto);

        ContaBancaria newContaBancaria = contaBancariaRepository.save(contaBancaria);
        
        return mapToDto(newContaBancaria);
    }

    @Override
    public ContaBancariaResponse getAllContasBancarias(String banco, int pageNo, int pageSize, String sortBy, String sortDir ) {

            Specification<ContaBancaria> spec = (root, query, criteriaBuilder) ->{

            List<Predicate> predicates = new ArrayList<>();


            if(banco != null){
                
                predicates.add(criteriaBuilder.equal(root.get("banco"), banco));
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<ContaBancaria> busca = contaBancariaRepository.findAll(spec, pageable);

        List<ContaBancaria> listOfContas = busca.getContent();

        List<ContaBancariaDto> content = listOfContas.stream().map(conta -> mapToDto(conta)).collect(Collectors.toList());

        ContaBancariaResponse response = new ContaBancariaResponse();

        response.setContent(content);
        response.setPageNo(busca.getNumber());
        response.setPageSize(busca.getSize());
        response.setTotalElements(busca.getTotalElements());
        response.setTotalPages(busca.getTotalPages());
        response.setLast(busca.isLast());

        return response;
    }

    private ContaBancaria mapToEntity(ContaBancariaDto contaBancariaDto){

        ContaBancaria contaBancaria =  new ContaBancaria();

        contaBancaria.setId(contaBancariaDto.getId());
        contaBancaria.setBanco(contaBancariaDto.getBanco());
        contaBancaria.setAgencia(contaBancariaDto.getAgencia());
        contaBancaria.setNumeroConta(contaBancariaDto.getNumeroConta());
        contaBancaria.setSaldo(contaBancariaDto.getSaldo());

        return contaBancaria;
    }

    private ContaBancariaDto mapToDto(ContaBancaria contaBancaria){

        ContaBancariaDto contaBancariaDto = new ContaBancariaDto();

        contaBancariaDto.setId(contaBancaria.getId());
        contaBancariaDto.setBanco(contaBancaria.getBanco());
        contaBancariaDto.setAgencia(contaBancaria.getAgencia());
        contaBancariaDto.setNumeroConta(contaBancaria.getNumeroConta());
        contaBancariaDto.setSaldo(contaBancaria.getSaldo());

        return contaBancariaDto;
    }

    
    
}
