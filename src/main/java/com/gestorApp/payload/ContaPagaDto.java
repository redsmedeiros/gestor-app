package com.gestorApp.payload;



import lombok.Data;

import java.util.Date;

@Data
public class ContaPagaDto {

    private long id;

    private String fornecedor;

    private Date dataEmissao;

    private Date dataVencimento;

    private String descricao;

    private double valorOriginal;

    private double valorPago;

    private double valorEmAberto;

    private String statusPagamento;

    private String metodoPagamento;

    private String numeroReferencia;

    private String notas;

    private int prioridade;

    private String departamento;

    private String responsavel;

    private Date dataPagamento;
}
