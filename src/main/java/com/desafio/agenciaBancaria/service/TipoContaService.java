package com.desafio.agenciaBancaria.service;

import com.desafio.agenciaBancaria.entity.TipoConta;
import com.desafio.agenciaBancaria.repository.TipoContaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoContaService {

    private final TipoContaRepository tipoContaRepository;


    public TipoContaService(TipoContaRepository tipoContaRepository) {
        this.tipoContaRepository = tipoContaRepository;
    }

    @Transactional
    public TipoConta cadastrarTipo( String nome ) {

        TipoConta tipo = new TipoConta( nome );

        return tipoContaRepository.save( tipo );
    }
}
