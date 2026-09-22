package com.desafio.agenciaBancaria.controller;

import com.desafio.agenciaBancaria.dto.ContaBancariaRequest;
import com.desafio.agenciaBancaria.entity.ContaBancaria;
import com.desafio.agenciaBancaria.service.ContaBancariaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/api/contas" )
public class ContaBancarioController {

    private final ContaBancariaService contaBancariaService;

    public ContaBancarioController(ContaBancariaService contaBancariaService) {
        this.contaBancariaService = contaBancariaService;
    }

    @PostMapping
    public ResponseEntity<ContaBancaria> abrirConta(@Valid @RequestBody ContaBancariaRequest request ) {

        ContaBancaria response = contaBancariaService.abrirConta( request );

        return ResponseEntity.status( HttpStatus.CREATED ).body( response );
    }

    @GetMapping
    public ResponseEntity<ContaBancaria> listarContas( Long id ) {

    }
}
