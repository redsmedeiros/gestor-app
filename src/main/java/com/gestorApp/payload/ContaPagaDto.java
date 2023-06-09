package com.gestorApp.payload;



import lombok.Data;

import java.util.Date;

@Data
public class ContaPagaDto {

    private long id;

    private Date dataEmissao;

    private Date dataVencimento;

    private String descricao;

    private Double valorOriginal;

    private Double valorPago;

    private Double valorEmAberto;

    private String statusPagamento;

    private String metodoPagamento;

    private String numeroReferencia;

    private String notas;

    private int prioridade;

    private String departamento;

    private String responsavel;

    private Date dataPagamento;
}
