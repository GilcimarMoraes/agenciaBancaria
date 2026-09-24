package com.desafio.agenciaBancaria.dto;

import java.time.Instant;
import java.time.LocalDateTime;

public record ApiErrorResponse(
        Instant timestamp,
        int status,
        String erro,
        String mensagem
) {
}
