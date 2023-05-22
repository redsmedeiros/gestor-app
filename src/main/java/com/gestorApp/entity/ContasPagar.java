package com.gestorApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "conta_pagar")
public class ContasPagar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "data_emissao")
    private Date dataEmissao;

    @Column(name = "data_vencimento")
    private Date dataVencimento;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor_original")
    private double valorOriginal;

    @Column(name = "valor_pago")
    private double valorPago;

    @Column(name = "valor_em_aberto")
    private double valorEmAberto;

    @Column(name = "status_pagamento")
    private String statusPagamento;

    @Column(name = "metodo_pagamento")
    private String metodoPagamento;

    @Column(name = "numero_referencia", unique = true)
    private String numeroReferencia;

    @Column(name = "notas")
    private String notas;

    @Column(name = "prioridade")
    private int prioridade;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "responsavel")
    private String responsavel;

    @Column(name = "data_pagamento")
    private Date dataPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;
}
