package com.desafio.agenciaBancaria.exception;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
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
    ResponseEntity<Map<String, Object>> dadosInvalidos(MethodArgumentNotValidException erro) {
        String detalhe = erro.getBindingResult().getFieldErrors().stream()
                .map(campo -> campo.getField() + ": " + campo.getDefaultMessage())
                .findFirst().orElse("Dados invalidos");
        return resposta(HttpStatus.BAD_REQUEST, detalhe);
    }

    /**
     * Converte contas com valor de movimentação inválida em um {@code 400 Not Found}
     * @param erro exceção lançada pela camada de aplicação
     * @return corpo de erro padronizado
     */
    @ExceptionHandler( ValorMovimentacaoInvalidoException.class )
    ResponseEntity<Map<String, Object>> valorInvalido( ValorMovimentacaoInvalidoException erro ) {
        return resposta( HttpStatus.BAD_REQUEST, erro.getMessage() );
    }

    /**
     * Converte contas inexistentes em um {@code 404 Not Found}
     * @param erro exceção lançada pela camada de aplicação
     * @return corpo de erro padronizado
     */
    @ExceptionHandler( ContaInexistenteException.class )
    ResponseEntity<Map<String, Object>> contaNaoEncontrada( ContaInexistenteException erro ) {
        return resposta( HttpStatus.NOT_FOUND, erro.getMessage() );
    }


    @ExceptionHandler( {AgenciaOuNumeroInexistenteException.class,
    ContaInativaException.class,
    CpfCadatradoException.class,
    SaldoInsuficienteException.class } )
    ResponseEntity<Map<String, Object>> conflito(AgenciaOuNumeroInexistenteException erro) {
        return resposta(HttpStatus.CONFLICT, erro.getMessage());
    }

    @ExceptionHandler( Exception.class )
    ResponseEntity<Map<String, Object>> generico( Exception erro ) {
        System.err.println( "Erro inesperado." );
        return resposta( HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno inesperado." );
    }

    private ResponseEntity<Map<String, Object>> resposta(HttpStatus status, String detalhe) {
        Map<String, Object> corpo = new LinkedHashMap<>();
        corpo.put("timestamp", Instant.now());
        corpo.put("status", status.value());
        corpo.put("erro", status.getReasonPhrase());
        corpo.put("detalhe", detalhe);
        return ResponseEntity.status(status).body(corpo);
    }
}

