package com.desafio.agenciaBancaria.dto;

import com.desafio.agenciaBancaria.entity.Pessoa;
import jakarta.validation.constraints.NotBlank;

public record PessoaResponse(
        Long id,
        String nome,
        int cpf,
        String email

) {

    public static PessoaResponse deEntity( Pessoa pessoa ) {
        return new PessoaResponse(
                pessoa.getId(),
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getEmail()
        );
    }
}
