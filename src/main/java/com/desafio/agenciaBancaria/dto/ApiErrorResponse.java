package com.desafio.agenciaBancaria.dto;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        LocalDateTime timestamp,
        int status,
        String erro,
        String mensagem
) {
}
