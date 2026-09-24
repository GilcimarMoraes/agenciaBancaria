package com.desafio.agenciaBancaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "pessoas" )
public class Pessoa {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( nullable = false, length = 120)
    private String nome;

    @Column( unique = true, nullable = false, length = 11)
    private String cpf;

    @Column( unique = true, nullable = false, length = 150)
    private String email;

    @OneToMany( fetch = FetchType.LAZY, mappedBy = "titular")
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
