package com.gestorApp.payload;

import lombok.Data;

@Data
public class FornecedorDto {
    
    private Long id;

    private String nome;

    private String cnpj;

    private String endereco;

    private String telefone;

    private String email;
}
