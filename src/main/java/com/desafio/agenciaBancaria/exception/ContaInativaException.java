package com.desafio.agenciaBancaria.exception;

public class ContaInativaException extends RuntimeException{

    public ContaInativaException() {
        super( "Conta Inativa" );
    }
}
