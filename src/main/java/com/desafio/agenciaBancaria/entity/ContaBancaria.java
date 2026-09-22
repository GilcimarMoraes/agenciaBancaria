package com.desafio.agenciaBancaria.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table( name = "contaBancaria" )
public class ContaBancaria {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private int agencia;

    private int numero;

    private BigDecimal saldo;

    private boolean ativo;

    @ManyToOne( fetch = FetchType.LAZY, optional = false)
    @JoinColumn( name = "titular_id" )
    private Pessoa pessoa;

    @ManyToOne( fetch = FetchType.LAZY, optional = false )
    @JoinColumn( name = "tipo_conta_id")
    private TipoConta tipoConta;

    public ContaBancaria() {}

    public ContaBancaria( int agencia, int numero, BigDecimal saldo, boolean ativo, Pessoa pessoa, TipoConta tipoConta) {
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
        this.ativo = ativo;
        this.pessoa = pessoa;
        this.tipoConta = tipoConta;
    }

    public void depositar( double valor ) {
        if( valor < 0 ) {
            throw new IllegalArgumentException( "Valor deve ser maior que zero." );
        }
        saldo += valor;
    }

    public void sacar( double valor ) {
        if( valor < 0 ) {
            throw new IllegalArgumentException( "Valor deve ser maior que zero." );
        }

        if( this.saldo < valor ) {
            throw new IllegalArgumentException( "Saldo insuficiente." );
        }

        saldo -= valor;
    }

    public void validarValor( BigDecimal valor ) {
        if( valor == null || valor.compareTo( BigDecimal.ZERO) <= 0 ) {
            throw new ValorMovimentacaoInvalidoException();
        }
    }



    public Long getId() {
        return id;
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }
}
