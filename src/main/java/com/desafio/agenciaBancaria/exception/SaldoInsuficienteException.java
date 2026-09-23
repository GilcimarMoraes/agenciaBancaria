package com.desafio.agenciaBancaria.exception;

public class SaldoInsuficienteException extends RuntimeException{

    public SaldoInsuficienteException ( String message ) {
        super( message );
    }
}
