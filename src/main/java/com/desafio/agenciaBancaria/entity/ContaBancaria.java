package com.desafio.agenciaBancaria.entity;

import com.desafio.agenciaBancaria.exception.ContaInativaException;
import com.desafio.agenciaBancaria.exception.SaldoInsuficienteException;
import com.desafio.agenciaBancaria.exception.ValorMovimentacaoInvalidoException;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table( name = "contas_bancarias" )
public class ContaBancaria {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private String agencia;

    private String numero;

    private BigDecimal saldo;

    private boolean ativa;

    @ManyToOne( fetch = FetchType.LAZY, optional = false)
    @JoinColumn( name = "titular_id" )
    private Pessoa titular;

    @ManyToOne( fetch = FetchType.LAZY, optional = false )
    @JoinColumn( name = "tipo_conta_id")
    private TipoConta tipoConta;

    public ContaBancaria() {}

    public ContaBancaria( String agencia, String numero, BigDecimal saldo,
                          boolean ativa, Pessoa titular, TipoConta tipoConta) {
        if( saldo == null || saldo.compareTo(BigDecimal.ZERO) < 0 ) {
            throw new ValorMovimentacaoInvalidoException(
                    "Saldo inicial não pode ser negativo."
            );
        }
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
        this.ativa = ativa;
        this.titular = titular;
        this.tipoConta = tipoConta;
    }

    public void depositar( BigDecimal valor ) {
        validarValor( valor );
        validarContaAtiva();

        this.saldo = this.saldo.add( valor );
    }

    public void sacar( BigDecimal valor ) {
        validarValor( valor );
        validarContaAtiva();
        if( this.saldo.compareTo( valor ) < 0 ) {
            throw new SaldoInsuficienteException(
                    "Saldo insuficiente: " +this.saldo + ", saque: " + valor
            );
        }

        this.saldo = this.saldo.subtract( valor );
    }

    public void validarValor( BigDecimal valor ) {
        if( valor == null || valor.compareTo( BigDecimal.ZERO) <= 0 ) {
            throw new ValorMovimentacaoInvalidoException(
                    "Valor da movimentação deve ser maior que zero."
            );
        }
    }

    public void validarContaAtiva() {
        if( !this.ativa ) {
            throw new ContaInativaException(
                    "Conta inativa, não pode ser movimentada."
            );
        }
    }



    public Long getId() {
        return id;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getNumero() {
        return numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public boolean isAtivo() {
        return ativa;
    }

    public Pessoa getPessoa() {
        return titular;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }
}
