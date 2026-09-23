package com.desafio.agenciaBancaria.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record PessoaRequest(
        @NotBlank( message = "Nome é obrigatório." )
        @Size( min = 3, max = 120, message = "Nome dever ter entre 3 e 120 caracteres." )
        String nome,

        @NotBlank
        @CPF( message = "Cpf inválido" )
        String cpf,

        @NotBlank( message = "O e-mail é obrigatório" )
        @Email( message = "E-mail inválido" )
        @Size( max = 150, message = "O email deve ter no máximo 150 caracteres" )
        String email
) {
}
