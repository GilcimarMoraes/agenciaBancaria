package com.desafio.agenciaBancaria.exception;

import java.time.Instant;
import java.util.stream.Collectors;

import com.desafio.agenciaBancaria.dto.ApiErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Traduz exceções da aplicação para respostas HTTP padronizadas.
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger( ApiExceptionHandler.class );
    /** Cria o tradutor de exceções usado por todos os controllers. */
    public ApiExceptionHandler() {
    }

    /**
     * Converte falhas de Bean Validation em {@code 400 Bad Request}.
     *
     * @param erro detalhes da validação
     * @return corpo de erro padronizado
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiErrorResponse> dadosInvalidos(MethodArgumentNotValidException erro) {
        String detalhe = erro.getBindingResult().getFieldErrors().stream()
                .map(campo -> campo.getField() + ": " + campo.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return resposta(HttpStatus.BAD_REQUEST, detalhe);
    }

    /**
     * Converte contas com valor de movimentação inválida em um {@code 400 Bad Request}
     * @param erro exceção lançada pela camada de aplicação
     * @return corpo de erro padronizado
     */
    @ExceptionHandler( ValorMovimentacaoInvalidoException.class )
    ResponseEntity<ApiErrorResponse> valorInvalido( ValorMovimentacaoInvalidoException erro ) {
        return resposta( HttpStatus.BAD_REQUEST, erro.getMessage() );
    }

    /**
     * Converte contas inexistentes em um {@code 404 Not Found}
     * @param erro exceção lançada pela camada de aplicação
     * @return corpo de erro padronizado
     */
    @ExceptionHandler( ContaInexistenteException.class )
    ResponseEntity<ApiErrorResponse> contaNaoEncontrada( ContaInexistenteException erro ) {
        return resposta( HttpStatus.NOT_FOUND, erro.getMessage() );
    }

    @ExceptionHandler( PessoaNaoEncontradaException.class )
    ResponseEntity<ApiErrorResponse> pessoaNaoEncontrada( PessoaNaoEncontradaException erro ) {
        return resposta( HttpStatus.NOT_FOUND, erro.getMessage() );
    }

    @ExceptionHandler( TipoContaNaoEncontradoException.class )
    ResponseEntity<ApiErrorResponse> tipoContaNaoEncontrado( TipoContaNaoEncontradoException erro ) {
        return resposta( HttpStatus.NOT_FOUND, erro.getMessage() );
    }


    @ExceptionHandler( {AgenciaNumeroJaCadastradoException.class,
    ContaInativaException.class,
    CpfCadatradosException.class,
    SaldoInsuficienteException.class } )
    ResponseEntity<ApiErrorResponse> conflito(RuntimeException erro) {
        return resposta(HttpStatus.CONFLICT, erro.getMessage());
    }

    @ExceptionHandler( Exception.class )
    ResponseEntity<ApiErrorResponse> generico( Exception erro ) {
        log.error( "Erro inesperado.", erro );
        return resposta( HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno inesperado." );
    }

    private ResponseEntity<ApiErrorResponse> resposta(HttpStatus status, String detalhe) {
        ApiErrorResponse body = new ApiErrorResponse(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                detalhe
        );

        return ResponseEntity.status( status ).body( body );
    }
}

