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
        if( tipoContaRepository.count() > 0 ) {
            log.info( "Tipos de conta já carregados. Pulando carga inicial." );

            return;
        }

        log.info( "Carregamento tipos de contas iniciais");
        for( TipoContaEnum tipo: TipoContaEnum.values() ) {
            tipoContaRepository.save( new TipoConta( tipo.getNome() ) );
            log.info( " -> Tipo de conta inserido: {}", tipo.getNome() );
        }

        log.info( "Carga inicial concluída. Total: {}", TipoContaEnum.values().length );
    }
}
