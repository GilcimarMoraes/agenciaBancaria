package com.desafio.agenciaBancaria.exception;

public class ContaInexistenteException extends RuntimeException {

    public ContaInexistenteException( Long id ) {
        super( "Conta com id: " +id + " inexistente." );
    }
}
