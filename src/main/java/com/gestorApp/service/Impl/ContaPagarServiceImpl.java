package com.gestorApp.service.Impl;

import com.gestorApp.entity.ContasPagar;
import com.gestorApp.entity.Fornecedor;
import com.gestorApp.enums.StatusPagamento;
import com.gestorApp.exception.GestorApiException;
import com.gestorApp.exception.ResourceNotFoundException;
import com.gestorApp.payload.ContaPagaDto;
import com.gestorApp.payload.ContaPagarResponse;
import com.gestorApp.repository.ContaPagarRepositoy;
import com.gestorApp.repository.FornecedorRepository;
import com.gestorApp.service.ContaPagarService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ContaPagarServiceImpl implements ContaPagarService {

    @Autowired
    private ContaPagarRepositoy contaPagarRepositoy;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Override
    public ContaPagaDto createContaPagar(long fornecedorId ,ContaPagaDto contaPagaDto) {

        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId).orElseThrow(()-> new ResourceNotFoundException("fornecedorId", "null", fornecedorId));

        ContasPagar contaPagar = mapToEntity(contaPagaDto);

        ContasPagar notaExiste = contaPagarRepositoy.findByNumeroReferencia(contaPagaDto.getNumeroReferencia());

        if(notaExiste != null){
            throw new EntityNotFoundException("Nota não encontrada");
        }

        contaPagar.setFornecedor(fornecedor);

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

                Specification<ContasPagar> spec = (root, query, criteriaBuilder) ->{

                    List<Predicate> predicates = new ArrayList<>();


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

    @Override
    public List<ContaPagaDto> findAllContaByFornecedorid(long fornecedorId, String statusPagamento, String dataVencimento){

        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId).orElseThrow(()-> new ResourceNotFoundException("fornecedorId", "id", fornecedorId));
        
        List<ContasPagar> contas = contaPagarRepositoy.findByFornecedorId(fornecedorId);

        List<ContaPagaDto> response = contas.stream().map(conta -> mapToDto(conta)).collect(Collectors.toList());

        if(statusPagamento != null){

            response = response.stream().filter(conta -> conta.getStatusPagamento().equalsIgnoreCase(statusPagamento)).collect(Collectors.toList());
        }


        if(dataVencimento != null){

            DateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            
            try {

                Date data = formatoData.parse(dataVencimento);

                response = response.stream()
                    .filter(conta ->{ 
                        
                        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

                        return formato.format(conta.getDataVencimento()).equals(formato.format(data));
                    })
                    .collect(Collectors.toList());

            } catch (ParseException e) {

                System.out.println("Erro ao converter a data de vencimento");
                return Collections.emptyList(); // ou outra ação apropriada
            }
            
            
        }

        return response;
    }

    @Override
    public ContaPagaDto findContaById(long fornecedorId, long contaId) {

        ContasPagar conta = contaPagarRepositoy.findById(contaId).orElseThrow(()-> new ResourceNotFoundException("contaPagarId", "id", contaId));

        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId).orElseThrow(()-> new ResourceNotFoundException("fornecedorId", "id", fornecedorId));
        
        if(!conta.getFornecedor().getId().equals(fornecedor.getId())){

            throw new GestorApiException(HttpStatus.BAD_REQUEST, "Conta não pertence ao fornecedor");
        }

        return mapToDto(conta);
    }

    @Override
    public ContaPagaDto updateContaById(long fornecedorId, long contaPagarId, ContaPagaDto contaPagaDto) {
        
        ContasPagar conta = contaPagarRepositoy.findById(contaPagarId).orElseThrow(()-> new ResourceNotFoundException("contaPagarId", "id", contaPagarId));

        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId).orElseThrow(()-> new ResourceNotFoundException("fornecedorId", "id", fornecedorId));
        
        if(!conta.getFornecedor().getId().equals(fornecedor.getId())){

            throw new GestorApiException(HttpStatus.BAD_REQUEST, "Conta não pertence ao fornecedor");
        }

        ContasPagar contaExists = contaPagarRepositoy.findByNumeroReferencia(contaPagaDto.getNumeroReferencia());

        if(contaExists != null){

            throw new EntityNotFoundException("Nota fiscal não casatrada");
        }

        if(contaPagaDto.getDataEmissao() != null){

            conta.setDataEmissao(contaPagaDto.getDataEmissao());
        }

        if(contaPagaDto.getDataVencimento() != null){

            conta.setDataVencimento(contaPagaDto.getDataVencimento());
        }

        if(contaPagaDto.getDescricao() != null){

            conta.setDescricao(contaPagaDto.getDescricao());
        }

        if(contaPagaDto.getValorOriginal() != null){

            conta.setValorOriginal(contaPagaDto.getValorOriginal());
        }

        if(contaPagaDto.getValorPago() != null){

            conta.setValorPago(contaPagaDto.getValorPago());
        }

        if(contaPagaDto.getValorEmAberto() != null){

            conta.setValorEmAberto(contaPagaDto.getValorEmAberto());
        }

        if(contaPagaDto.getMetodoPagamento() != null){

            conta.setMetodoPagamento(contaPagaDto.getMetodoPagamento());
        }

        if(contaPagaDto.getNumeroReferencia() != null){

            conta.setNumeroReferencia(contaPagaDto.getNumeroReferencia());
        }

        if(contaPagaDto.getNotas() != null){

            conta.setNotas(contaPagaDto.getNotas());
        }

        Integer prioridade = contaPagaDto.getPrioridade();

        if(prioridade != null){

            conta.setPrioridade(contaPagaDto.getPrioridade());
        }

        if(conta.getDepartamento() != null){

            conta.setPrioridade(contaPagaDto.getPrioridade());
        }

        if(conta.getResponsavel() != null){

            conta.setResponsavel(contaPagaDto.getResponsavel());
        }

        if(conta.getDataPagamento() != null){

            conta.setDataPagamento(conta.getDataPagamento());
        }
 
        ContasPagar updatedConta = contaPagarRepositoy.save(conta);

        return mapToDto(updatedConta);
    }

    @Override
    public void deleteContaById(long fornecedorId, long contaPagarId) {
       
        ContasPagar conta = contaPagarRepositoy.findById(contaPagarId).orElseThrow(()-> new ResourceNotFoundException("contaPagarId", "id", contaPagarId));

        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId).orElseThrow(()-> new ResourceNotFoundException("fornecedorId", "id", fornecedorId));
        
        if(!conta.getFornecedor().getId().equals(fornecedor.getId())){

            throw new GestorApiException(HttpStatus.BAD_REQUEST, "Conta não pertence ao fornecedor");
        }

        contaPagarRepositoy.delete(conta);
        
    }

    private ContasPagar mapToEntity(ContaPagaDto contaPagaDto){

        ContasPagar contasPagar = new ContasPagar();

        contasPagar.setId(contaPagaDto.getId());
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
