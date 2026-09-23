package com.desafio.agenciaBancaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "tabela" )
public class Pessoa {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String nome;

    private String cpf;

    private String email;

    @OneToMany( fetch = FetchType.LAZY )
    private List<ContaBancaria> contas = new ArrayList<>();

    public Pessoa() {}

    public Pessoa( String nome, String cpf, String email ) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }
}
