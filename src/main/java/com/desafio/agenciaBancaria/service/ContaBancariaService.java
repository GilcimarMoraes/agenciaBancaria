package com.desafio.agenciaBancaria.service;

import com.desafio.agenciaBancaria.dto.ContaBancariaRequest;
import com.desafio.agenciaBancaria.dto.ContaBancariaResponse;
import com.desafio.agenciaBancaria.entity.ContaBancaria;
import com.desafio.agenciaBancaria.entity.Pessoa;
import com.desafio.agenciaBancaria.entity.TipoConta;
import com.desafio.agenciaBancaria.repository.ContaBancariaRepository;
import com.desafio.agenciaBancaria.repository.PessoaRepository;
import com.desafio.agenciaBancaria.repository.TipoContaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContaBancariaService {

    private final ContaBancariaRepository contaBancariaRepository;

    private final PessoaRepository pessoaRepository;

    private final TipoContaRepository tipoContaRepository;

    public ContaBancariaService(ContaBancariaRepository contaBancariaRepository,
                                PessoaRepository pessoaRepository,
                                TipoContaRepository tipoContaRepository) {
        this.contaBancariaRepository = contaBancariaRepository;
        this.pessoaRepository = pessoaRepository;
        this.tipoContaRepository = tipoContaRepository;
    }


    @Transactional
    public ContaBancaria abrirConta(ContaBancariaRequest request ) {
        Pessoa pessoa = pessoaRepository.findById( request.titularId() )
                .orElseThrow( () -> new RuntimeException( "Nao encontrado." ) );

        TipoConta tipo = tipoContaRepository.findById( request.tipoContaId() )
                .orElseThrow( () -> new RuntimeException( "tipo nao encontrado" ) );

        ContaBancaria conta = new ContaBancaria( request.agencia(), request.numero(), request.saldoInicial(), request.ativa(),
                pessoa, tipo );

        return contaBancariaRepository.save( conta );
    }

}
