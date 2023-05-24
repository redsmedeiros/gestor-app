package com.gestorApp.payload;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDto {

    private long id;

    private BigDecimal valor;

    private LocalDate data;

    private String formaPagamento;

    private String status;
    
}
