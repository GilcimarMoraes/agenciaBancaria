package com.desafio.agenciaBancaria.exception;

public class CpfCadatradosException extends RuntimeException{

    public CpfCadatradosException(String cpf ) {
        super( "O CPF: " + cpf + " já possui cadastro." );
    }
}
