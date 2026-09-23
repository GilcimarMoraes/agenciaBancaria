package com.desafio.agenciaBancaria.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record ContaBancariaRequest(
        @NotBlank( message = "A agência é obrigatória" )
        @Pattern( regexp = "\\d{4}", message = "A agência deve conter exatamente 4 digitos." )
        String agencia,

        @NotBlank( message = "O número da conta é obrigatório" )
        @Pattern( regexp = "\\d{6}-\\d", message = "O número deve seguir o formato 123456-7" )
        int numero,

        @NotNull( message = "O saldo inicial é obrigatório" )
        @DecimalMin( value = "0.00", inclusive = true,
        message = "Saldo inicial não pode ser negativo" )
        BigDecimal saldoInicial,

        @NotNull( message = "O campo 'ativa' é obrigatória." )
        boolean ativa,

        @NotNull( message = "O titularId é obrigatório." )
        Long titularId,

        @NotNull( message = "O titularId é obrigatório." )
        Long tipoContaId
) {
}
