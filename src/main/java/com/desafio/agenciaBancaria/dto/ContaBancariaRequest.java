package com.desafio.agenciaBancaria.dto;

import jakarta.validation.constraints.NotNull;

public record ContaBancariaRequest(
        @NotNull int agencia,
        @NotNull int numero,
        @NotNull double saldoInicial,
        boolean ativa,
        Long titularId,
        Long tipoContaId
) {
}
