package com.desafio.agenciaBancaria.exception;

public class CpfCadatradoException extends RuntimeException{

    public CpfCadatradoException(String cpf ) {
        super( "O CPF: " + cpf + " já possui cadastro." );
    }
}
