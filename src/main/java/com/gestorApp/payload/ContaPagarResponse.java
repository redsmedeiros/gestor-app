package com.gestorApp.payload;

import java.util.List;

public class ContaPagarResponse {
    
    private List<ContaPagaDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
