package com.gestorApp.exception;

import org.springframework.http.HttpStatus;

public class GestorApiException extends RuntimeException{
    
    private HttpStatus status;

    private String message;

    public GestorApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public GestorApiException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
