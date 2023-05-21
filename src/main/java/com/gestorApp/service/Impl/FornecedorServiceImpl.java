package com.gestorApp.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gestorApp.entity.Fornecedor;
import com.gestorApp.exception.ResourceNotFoundException;
import com.gestorApp.payload.FornecedorDto;
import com.gestorApp.repository.FornecedorRepository;
import com.gestorApp.service.FornecedorService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;

@Service
public class FornecedorServiceImpl implements FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Override
    public FornecedorDto createFornecedor(FornecedorDto fornecedorDto) {

        Fornecedor fornecedorExists = fornecedorRepository.findByCnpj(fornecedorDto.getCnpj());

        if(fornecedorExists != null){
            throw new EntityNotFoundException("Fornecedor não encontrado");
        }
        
        Fornecedor fornecedor = mapToEntity(fornecedorDto);

        System.out.println(fornecedor);

        Fornecedor newFornecedor = fornecedorRepository.save(fornecedor);
        
        return mapToDto(newFornecedor);
    }

    @Override
    public List<FornecedorDto> getAllFornecedores() {
        
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();

        List<FornecedorDto> response = fornecedores.stream().map(fornecedor -> mapToDto(fornecedor)).collect(Collectors.toList());

        return response;
    }

    @Override
    public FornecedorDto getFornecedorById(long fornecedorId) {
        
        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId).orElseThrow(()-> new ResourceNotFoundException("fornecedorId", "id", fornecedorId)); 


        return mapToDto(fornecedor);
    }

    @Override
    public FornecedorDto updateFornecedorByid(long fornecedorId, FornecedorDto fornecedorDto) {
        
        Fornecedor cnpjExits = fornecedorRepository.findByCnpj(fornecedorDto.getCnpj());

        if(cnpjExits != null){
            
            throw new EntityNotFoundException("CNPj já cadastrado"); 
        }

        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId).orElseThrow(()-> new ResourceNotFoundException("fornecedorId", "id", fornecedorId));

        if(fornecedorDto.getNome() != null){
            
            fornecedor.setNome(fornecedorDto.getNome());
        }

        if(fornecedorDto.getCnpj() != null){

            fornecedor.setCnpj(fornecedorDto.getCnpj());
        }
       
        if(fornecedorDto.getEmail() != null){

            fornecedor.setEmail(fornecedorDto.getEmail());
        }

        if(fornecedorDto.getEndereco() != null){

            fornecedor.setEndereco(fornecedorDto.getEndereco());
        }

        if(fornecedorDto.getTelefone() != null){

            fornecedor.setTelefone(fornecedorDto.getTelefone());
        }

        Fornecedor updatedFornecedor = fornecedorRepository.save(fornecedor); 

        return mapToDto(updatedFornecedor);
    }

    @Override
    public void deleteFornecedorById(long fornecedorId) {
        
        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId).orElseThrow(()-> new ResourceNotFoundException("fornecedorId", "id", fornecedorId));


        fornecedorRepository.delete(fornecedor);
    }

    @Override
    public List<FornecedorDto> buscarFornecedores(String nome, String cnpj, int pageNo, int pageSize, String sortBy) {
        
        Specification<Fornecedor> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (nome != null) {
                predicates.add(criteriaBuilder.equal(root.get("nome"), nome));
            }
            if (cnpj != null) {
                predicates.add(criteriaBuilder.equal(root.get("cnpj"), cnpj));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Fornecedor> busca = fornecedorRepository.findAll(spec, pageable);

        List<FornecedorDto> response = busca.stream().map(fornecedor -> mapToDto(fornecedor)).collect(Collectors.toList());

        return response;
    }

    private Fornecedor mapToEntity(FornecedorDto fornecedorDto){

        Fornecedor fornecedor = new Fornecedor();

        fornecedor.setId(fornecedorDto.getId());
        fornecedor.setNome(fornecedorDto.getNome());
        fornecedor.setEmail(fornecedorDto.getEmail());
        fornecedor.setCnpj(fornecedorDto.getCnpj());
        fornecedor.setEndereco(fornecedorDto.getEndereco());
        fornecedor.setTelefone(fornecedorDto.getTelefone());

        return fornecedor;
    }

    private FornecedorDto mapToDto(Fornecedor fornecedor){

        FornecedorDto fornecedorDto = new FornecedorDto();

        fornecedorDto.setId(fornecedor.getId());
        fornecedorDto.setNome(fornecedor.getNome());
        fornecedorDto.setEmail(fornecedor.getEmail());
        fornecedorDto.setCnpj(fornecedor.getCnpj());
        fornecedorDto.setEndereco(fornecedor.getEndereco());
        fornecedorDto.setTelefone(fornecedor.getTelefone());

        return fornecedorDto;
    }

    

   

   

    

   
    
}
