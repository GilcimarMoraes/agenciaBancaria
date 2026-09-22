package com.desafio.agenciaBancaria.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PessoaRequest(
        @NotBlank String nome,
        @NotNull int cpf,
        @Email String email
) {
}
