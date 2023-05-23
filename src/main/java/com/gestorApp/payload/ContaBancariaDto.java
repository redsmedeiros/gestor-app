package com.gestorApp.payload;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaBancariaDto {
    
    private long id;

    private String banco;

  
    private String agencia;

  
    private String numeroConta;

   
    private BigDecimal saldo;
}
