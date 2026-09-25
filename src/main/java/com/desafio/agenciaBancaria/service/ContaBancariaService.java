package com.desafio.agenciaBancaria.service;

import com.desafio.agenciaBancaria.dto.ContaBancariaRequest;
import com.desafio.agenciaBancaria.dto.ContaBancariaResponse;
import com.desafio.agenciaBancaria.dto.MovimentacaoRequest;
import com.desafio.agenciaBancaria.entity.ContaBancaria;
import com.desafio.agenciaBancaria.entity.Pessoa;
import com.desafio.agenciaBancaria.entity.TipoConta;
import com.desafio.agenciaBancaria.exception.*;
import com.desafio.agenciaBancaria.repository.ContaBancariaRepository;
import com.desafio.agenciaBancaria.repository.PessoaRepository;
import com.desafio.agenciaBancaria.repository.TipoContaRepository;
import com.desafio.agenciaBancaria.transacoes.Transacoes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContaBancariaService {

    private final ContaBancariaRepository contaBancariaRepository;

    private final PessoaRepository pessoaRepository;

    private final TipoContaRepository tipoContaRepository;

    private final Transacoes transacao;

    public ContaBancariaService(ContaBancariaRepository contaBancariaRepository,
                                PessoaRepository pessoaRepository,
                                TipoContaRepository tipoContaRepository, Transacoes transacao) {
        this.contaBancariaRepository = contaBancariaRepository;
        this.pessoaRepository = pessoaRepository;
        this.transacao = transacao;
        this.tipoContaRepository = tipoContaRepository;
    }

    @Transactional( readOnly = true )
    public ContaBancariaResponse buscarPorId( Long id ) {
        ContaBancaria conta = contaBancariaRepository.findById( id )
                .orElseThrow( () -> new ContaInexistenteException( id ));

        return ContaBancariaResponse.deEntity( conta );
    }


    @Transactional
    public ContaBancariaResponse abrirConta(ContaBancariaRequest request ) {

        Pessoa pessoa = pessoaRepository.findById( request.titularId() )
                .orElseThrow( () -> new PessoaNaoEncontradaException( request.titularId() ) );

        TipoConta tipo = tipoContaRepository.findById( request.tipoContaId() )
                .orElseThrow( () -> new TipoContaNaoEncontradoException( request.tipoContaId() ) );

        if( contaBancariaRepository.existsByAgenciaAndNumero( request.agencia(), request.numero() ) ){
            throw new AgenciaNumeroJaCadastradoException( request.agencia(), request.numero() );
        }

        ContaBancaria conta = new ContaBancaria( request.agencia(), request.numero(), request.saldoInicial(), request.ativa(),
                pessoa, tipo );

        contaBancariaRepository.save( conta );

        return ContaBancariaResponse.deEntity( conta );

    }

    @Transactional( readOnly = true )
    public List<ContaBancariaResponse> listarContaPorPessoa( Long id ) {
        List<ContaBancaria> lista = contaBancariaRepository.findByTitularId( id );

        return lista.stream().map( ContaBancariaResponse::deEntity ).toList();
    }

    private ContaBancaria buscarPorEntidadeId( Long id ) {

        return contaBancariaRepository.findById( id )
                .orElseThrow( () -> new ContaInexistenteException( id ) );
    }

    @Transactional
    public ContaBancariaResponse depositar ( Long id, MovimentacaoRequest request ) {

        ContaBancaria conta = buscarPorEntidadeId( id );


        transacao.depositar( conta, request.valor() );

        return ContaBancariaResponse.deEntity( conta );
    }

    @Transactional
    public ContaBancariaResponse sacar( Long id, MovimentacaoRequest request ) {

        ContaBancaria conta = buscarPorEntidadeId( id );

        transacao.sacar( conta, request.valor() );

        return ContaBancariaResponse.deEntity( conta );
    }

}
