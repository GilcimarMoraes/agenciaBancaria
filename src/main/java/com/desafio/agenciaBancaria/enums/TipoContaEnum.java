package com.desafio.agenciaBancaria.enums;

public enum TipoContaEnum {
    CORRENTE ( "Conta Corrente" ),
    POUPANCA ( "Conta Poupança" ),
    SALARIO ( "Conta Salário" );

    private final String nome;
    TipoContaEnum(String nome ) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
