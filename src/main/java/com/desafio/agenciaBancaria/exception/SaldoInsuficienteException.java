package com.desafio.agenciaBancaria.exception;

public class SaldoInsuficienteException extends RuntimeException{

    public SaldoInsuficienteException () {
        super( "Saldo insuficiente" );
    }
}
