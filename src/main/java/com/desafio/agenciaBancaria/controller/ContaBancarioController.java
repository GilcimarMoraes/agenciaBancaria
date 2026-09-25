package com.desafio.agenciaBancaria.controller;

import com.desafio.agenciaBancaria.controller.mappers.GenericController;
import com.desafio.agenciaBancaria.dto.ContaBancariaRequest;
import com.desafio.agenciaBancaria.dto.ContaBancariaResponse;
import com.desafio.agenciaBancaria.dto.MovimentacaoRequest;
import com.desafio.agenciaBancaria.service.ContaBancariaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping( "/api/contas" )
public class ContaBancarioController implements GenericController {

    private final ContaBancariaService contaBancariaService;

    public ContaBancarioController(ContaBancariaService contaBancariaService) {
        this.contaBancariaService = contaBancariaService;
    }

    @PostMapping
    @Operation( summary = "Abrir conta." )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Conta criada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "409", description = "CPF já cadastrado."),
            @ApiResponse(responseCode = "409", description = "Agência ou Conta cadastrada.")
    })

    public ResponseEntity<ContaBancariaResponse> abrirConta(@Valid @RequestBody ContaBancariaRequest request ) {

        ContaBancariaResponse response = contaBancariaService.abrirConta( request );

        URI localizacao = gerarHeaderLocation( response.id() );

        return ResponseEntity.created( localizacao ).body( response );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<ContaBancariaResponse> buscarPorId( @Valid @PathVariable Long id ) {
        ContaBancariaResponse response = contaBancariaService.buscarPorId( id );

        return ResponseEntity.ok( response );
    }

    @GetMapping( "/pessoas/{pessoaId}" )
    public ResponseEntity<List<ContaBancariaResponse>> listar( @Valid @PathVariable Long pessoaId ) {
        List<ContaBancariaResponse> lista = contaBancariaService.listarContaPorPessoa( pessoaId );

        return ResponseEntity.ok( lista );
    }

    @PatchMapping( "/{id}/depositos" )
    public ResponseEntity<ContaBancariaResponse> depositar( @Valid @PathVariable Long id,
                                                           @Valid @RequestBody MovimentacaoRequest request ) {
        ContaBancariaResponse deposito = contaBancariaService.depositar( id, request );

        return ResponseEntity.ok( deposito );
    }

    @PatchMapping( "/{id}/saques" )
    public ResponseEntity<ContaBancariaResponse> sacar( @Valid @PathVariable Long id,
                                                        @Valid @RequestBody MovimentacaoRequest request ) {
        ContaBancariaResponse saque = contaBancariaService.sacar( id, request );

        return ResponseEntity.ok( saque );
    }
}
