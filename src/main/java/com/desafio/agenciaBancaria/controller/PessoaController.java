package com.desafio.agenciaBancaria.controller;

import com.desafio.agenciaBancaria.controller.mappers.GenericController;
import com.desafio.agenciaBancaria.dto.PessoaRequest;
import com.desafio.agenciaBancaria.dto.PessoaResponse;
import com.desafio.agenciaBancaria.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Tag( name = "Pessoas" )
@RestController
@RequestMapping ( "/api/pessoas" )
public class PessoaController implements GenericController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    @Operation( summary = "Cadastrar cliente." )
    @ApiResponses ({
            @ApiResponse( responseCode = "201", description = "Cliente Cadastrado"),
            @ApiResponse( responseCode = "400", description = "Dados inválidos"),
            @ApiResponse( responseCode = "409", description = "CPF já cadastrado." )

    })
    public ResponseEntity<PessoaResponse> cadastrar( @Valid @RequestBody PessoaRequest request ) {

        PessoaResponse response = pessoaService.cadastrar( request );

        URI localizacao = gerarHeaderLocation( response.id() );

        return ResponseEntity.created( localizacao ).body( response );
    }
}
