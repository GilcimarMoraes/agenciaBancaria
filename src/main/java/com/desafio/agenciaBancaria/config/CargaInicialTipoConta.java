package com.desafio.agenciaBancaria.config;

import com.desafio.agenciaBancaria.entity.TipoConta;
import com.desafio.agenciaBancaria.enums.TipoContaEnum;
import com.desafio.agenciaBancaria.repository.TipoContaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

public class CargaInicialTipoConta implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger( CargaInicialTipoConta.class );

    private final TipoContaRepository tipoContaRepository;

    public CargaInicialTipoConta(TipoContaRepository tipoContaRepository) {
        this.tipoContaRepository = tipoContaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        for( TipoContaEnum tipo: TipoContaEnum.values() ) {
            if( tipoContaRepository.existsByNome( tipo.getNome() ) ) {
                log.info( " -> Já existe: {}", tipo.getNome() );
                continue;
            }
            tipoContaRepository.save( new TipoConta( tipo.getNome() ) );
            log.info( " -> Novo tipo inserido: {}", tipo.getNome() );
        }

        log.info( "Carga de tipos de conta verificada." );
    }
}
