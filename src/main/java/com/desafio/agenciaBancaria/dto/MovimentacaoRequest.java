package com.desafio.agenciaBancaria.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record MovimentacaoRequest(
        @NotNull( message = "O valor é obrigatório" )
        @DecimalMin( value = "0.01", inclusive = true,
        message = "O valor da movimentação deve ser maior que zero" )
        BigDecimal valor
) {
}
