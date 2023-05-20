package com.gestorApp.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestorApp.entity.Fornecedor;
import com.gestorApp.exception.ResourceNotFoundException;
import com.gestorApp.payload.FornecedorDto;
import com.gestorApp.repository.FornecedorRepository;
import com.gestorApp.service.FornecedorService;

import jakarta.persistence.EntityNotFoundException;

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
