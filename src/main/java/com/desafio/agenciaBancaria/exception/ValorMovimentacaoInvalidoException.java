package com.desafio.agenciaBancaria.exception;

public class ValorMovimentacaoInvalidoException extends RuntimeException{

    public ValorMovimentacaoInvalidoException() {
        super( "O valor deve ser maior que zero" );
    }
}
