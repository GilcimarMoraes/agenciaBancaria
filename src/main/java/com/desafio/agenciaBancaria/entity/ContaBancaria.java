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
            throw new ValorMovimentacaoInvalidoException();
        }
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
        this.ativa = ativa;
        this.titular = titular;
        this.tipoConta = tipoConta;
    }

    public void creditar( BigDecimal valor ) {
        validarValor( valor );
        validarConta();

        this.saldo = this.saldo.add( valor );
    }

    public void debitar( BigDecimal valor ) {
        validarValor( valor );
        validarConta();
        validarSaldo( valor );

        this.saldo = this.saldo.subtract( valor );
    }

    private void validarConta() {
        if( !isAtiva() ) {
            throw new ContaInativaException();
        }
    }

    private void validarSaldo( BigDecimal valor ) {
        if( saldo.compareTo( valor ) < 0 ){
            throw new SaldoInsuficienteException();
        }
    }

    private void validarValor( BigDecimal valor ) {
        if( valor == null || valor.compareTo( BigDecimal.ZERO ) <= 0 ) {
            throw new ValorMovimentacaoInvalidoException();
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

    public boolean isAtiva() {
        return ativa;
    }

    public Pessoa getPessoa() {
        return titular;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }
}
