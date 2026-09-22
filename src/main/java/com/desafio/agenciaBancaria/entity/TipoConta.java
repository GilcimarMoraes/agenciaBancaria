package com.desafio.agenciaBancaria.entity;

import jakarta.persistence.*;

@Entity
@Table( name = "tipoConta" )
public class TipoConta {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String nome;

    public TipoConta() {}

    public TipoConta( String nome ) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
