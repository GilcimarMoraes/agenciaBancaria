package com.desafio.agenciaBancaria.controller;

import com.desafio.agenciaBancaria.dto.PessoaRequest;
import com.desafio.agenciaBancaria.dto.PessoaResponse;
import com.desafio.agenciaBancaria.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag( name = "Pessoas" )
@RestController
@RequestMapping ( "/api/pessoas" )
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    @Operation( summary = "Cadastrar cliente." )
    @ApiResponses ({
            @ApiResponse( responseCode = "201", description = "Cliente Cadastrado"),
            @ApiResponse( responseCode = "400", description = "Dados inválidos")

    })
    public ResponseEntity<PessoaResponse> cadastrar( @Valid @RequestBody PessoaRequest request ) {

        PessoaResponse response = pessoaService.cadastrar( request );

        return ResponseEntity.status(HttpStatus.CREATED).body( response );
    }
}
