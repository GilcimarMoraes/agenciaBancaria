package com.desafio.agenciaBancaria.dto;

import com.desafio.agenciaBancaria.entity.ContaBancaria;
import com.desafio.agenciaBancaria.entity.Pessoa;
import com.desafio.agenciaBancaria.entity.TipoConta;

import java.math.BigDecimal;

public record ContaBancariaResponse(
        Long id,
        String agencia,
        String numero,
        BigDecimal saldo,
        Long titularId,
        String titularNome,
        Long tipoId,
        String tipoNome
) {

    public static ContaBancariaResponse deEntity( ContaBancaria contaBancaria ) {
        return new ContaBancariaResponse(
                contaBancaria.getId(),
                contaBancaria.getAgencia(),
                contaBancaria.getNumero(),
                contaBancaria.getSaldo(),
                contaBancaria.getPessoa().getId(),
                contaBancaria.getPessoa().getNome(),
                contaBancaria.getTipoConta().getId(),
                contaBancaria.getTipoConta().getNome()

        );
    }
}
