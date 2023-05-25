package com.gestorApp.payload;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmaPagamentoDto {
    
    private boolean confirmaPagamento;

    private LocalDate dataConfirmacao;
}
