package com.desafio.agenciaBancaria.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MovimentacaoRequest(
        @NotNull @Positive double valor
) {
}
