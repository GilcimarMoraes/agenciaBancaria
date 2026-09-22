package com.desafio.agenciaBancaria.dto;

import com.desafio.agenciaBancaria.entity.ContaBancaria;
import com.desafio.agenciaBancaria.entity.Pessoa;
import com.desafio.agenciaBancaria.entity.TipoConta;

public record ContaBancariaResponse(
        Long id,
        int agencia,
        int numero,
        double saldo,
        Long titularId,
        Long tipoId
) {

    public ContaBancariaResponse deEntity( ContaBancaria contaBancaria ) {
        return new ContaBancariaResponse(
                contaBancaria.getId(),
                contaBancaria.getAgencia(),
                contaBancaria.getNumero(),
                contaBancaria.getSaldo(),
                contaBancaria.getPessoa().getId(),
                contaBancaria.getTipoConta().getId()

        );
    }
}
