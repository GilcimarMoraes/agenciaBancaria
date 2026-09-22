package com.desafio.agenciaBancaria.service;

import com.desafio.agenciaBancaria.dto.PessoaRequest;
import com.desafio.agenciaBancaria.dto.PessoaResponse;
import com.desafio.agenciaBancaria.entity.Pessoa;
import com.desafio.agenciaBancaria.repository.PessoaRepository;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public PessoaResponse cadastrar( PessoaRequest request ) {

        Pessoa pessoa = new Pessoa( request.nome(), request.cpf(), request.email());

        pessoaRepository.save( pessoa );

        return PessoaResponse.deEntity( pessoa );

    }
}
