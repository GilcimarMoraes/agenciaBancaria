package com.desafio.agenciaBancaria.exception;

public class TipoContaNaoEncontradoException extends RuntimeException{

    public TipoContaNaoEncontradoException( Long id ) {
        super( "Tipo conta " + id + " não encontrado." );
    }
}
