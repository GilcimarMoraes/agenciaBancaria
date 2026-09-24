package com.desafio.agenciaBancaria.exception;

public class AgenciaNumeroJaCadastradoException extends RuntimeException {

    public AgenciaNumeroJaCadastradoException(String agencia, String numero ) {
        super( "A agencia ou número de conta inexistente. ");
    }
}
