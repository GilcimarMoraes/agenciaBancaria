package com.desafio.agenciaBancaria.exception;

public class CpfCadastradoException extends RuntimeException{

    public CpfCadastradoException(String cpf ) {
        super( "O CPF: " + cpf + " já possui cadastro." );
    }
}
