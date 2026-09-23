package com.desafio.agenciaBancaria.exception;

public class AgenciaOuNumeroInexistenteException extends RuntimeException {

    public AgenciaOuNumeroInexistenteException( String agencia, String numero ) {
        super( "A agencia ou número de conta inexistente. ");
    }
}
