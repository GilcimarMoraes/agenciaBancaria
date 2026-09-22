package com.desafio.agenciaBancaria.controller;

import com.desafio.agenciaBancaria.entity.TipoConta;
import com.desafio.agenciaBancaria.service.TipoContaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/tipos" )
public class TipoContaController {

    private final TipoContaService tipoContaService;


    public TipoContaController(TipoContaService tipoContaService) {
        this.tipoContaService = tipoContaService;
    }

    @PostMapping
    public ResponseEntity<TipoConta> criarTipo( @RequestBody String nome ) {

        TipoConta tipo = tipoContaService.cadastrarTipo( nome );

        return ResponseEntity.status( HttpStatus.CREATED ).body( tipo );
    }
}
