package com.desafio.agenciaBancaria.dto;

import com.desafio.agenciaBancaria.entity.Pessoa;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        LocalDateTime timestamp,
        int status,
        String erro,
        String mensagem,
        String caminho
) {
}
