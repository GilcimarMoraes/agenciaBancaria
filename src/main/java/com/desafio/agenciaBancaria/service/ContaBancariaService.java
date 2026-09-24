package com.desafio.agenciaBancaria.service;

import com.desafio.agenciaBancaria.dto.ContaBancariaRequest;
import com.desafio.agenciaBancaria.dto.ContaBancariaResponse;
import com.desafio.agenciaBancaria.dto.MovimentacaoRequest;
import com.desafio.agenciaBancaria.entity.ContaBancaria;
import com.desafio.agenciaBancaria.entity.Pessoa;
import com.desafio.agenciaBancaria.entity.TipoConta;
import com.desafio.agenciaBancaria.exception.AgenciaNumeroJaCadastradoException;
import com.desafio.agenciaBancaria.exception.ContaInexistenteException;
import com.desafio.agenciaBancaria.exception.PessoaNaoEncontradaException;
import com.desafio.agenciaBancaria.exception.TipoContaNaoEncontradoException;
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

    public ContaBancariaResponse buscarPorId( Long id ) {
        ContaBancaria conta = contaBancariaRepository.findById( id )
                .orElseThrow( () -> new ContaInexistenteException( id ));

        return ContaBancariaResponse.deEntity( conta );
    }


    @Transactional
    public ContaBancariaResponse abrirConta(ContaBancariaRequest request ) {

        if( contaBancariaRepository.existsByAgenciaAndNumero( request.agencia(), request.numero() ) ){
            throw new AgenciaNumeroJaCadastradoException( request.agencia(), request.numero() );
        }

        Pessoa pessoa = pessoaRepository.findById( request.titularId() )
                .orElseThrow( () -> new PessoaNaoEncontradaException( request.titularId() ) );

        TipoConta tipo = tipoContaRepository.findById( request.tipoContaId() )
                .orElseThrow( () -> new TipoContaNaoEncontradoException( request.tipoContaId() ) );

        ContaBancaria conta = new ContaBancaria( request.agencia(), request.numero(), request.saldoInicial(), request.ativa(),
                pessoa, tipo );

        contaBancariaRepository.save( conta );

        return ContaBancariaResponse.deEntity( conta );

    }

    private ContaBancaria buscarPorEntidadeId( Long id ) {

        return contaBancariaRepository.findById( id )
                .orElseThrow( () -> new ContaInexistenteException( id ) );
    }

    @Transactional
    public ContaBancariaResponse depositar ( Long id, MovimentacaoRequest request ) {

        ContaBancaria conta = buscarPorEntidadeId( id );

        conta.depositar( request.valor() );

        return ContaBancariaResponse.deEntity( conta );
    }

    @Transactional
    public ContaBancariaResponse sacar( Long id, MovimentacaoRequest request ) {

        ContaBancaria conta = buscarPorEntidadeId( id );

        conta.sacar( request.valor() );

        return ContaBancariaResponse.deEntity( conta );
    }

}
