package com.desafio.agenciaBancaria.service;

import com.desafio.agenciaBancaria.dto.PessoaRequest;
import com.desafio.agenciaBancaria.dto.PessoaResponse;
import com.desafio.agenciaBancaria.entity.Pessoa;
import com.desafio.agenciaBancaria.exception.CpfCadatradosException;
import com.desafio.agenciaBancaria.repository.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional
    public PessoaResponse cadastrar( PessoaRequest request ) {

        if(pessoaRepository.existsByCpf( request.cpf() ) ){
            throw new CpfCadatradosException( request.cpf() );
        }

        Pessoa pessoa = new Pessoa( request.nome(), request.cpf(), request.email());

        pessoaRepository.save( pessoa );

        return PessoaResponse.deEntity( pessoa );

    }
}
