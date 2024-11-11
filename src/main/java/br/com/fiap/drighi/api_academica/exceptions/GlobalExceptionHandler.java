package br.com.fiap.drighi.api_academica.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlunoException.class)
    public ResponseEntity<ErrorResponseDTO> handleAlunoException(AlunoException ex, WebRequest request) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                LocalDateTime.now(),
                ex.getMessage()
        );

        if (ex.getExceptionType() == AlunoExceptionEnum.ALUNO_NAO_ENCONTRADO) {
            return new ResponseEntity<>(errorResponseDTO, HttpStatus.NOT_FOUND);
        } else if (ex.getExceptionType() == AlunoExceptionEnum.ALUNO_JA_EXISTE) {
            return new ResponseEntity<>(errorResponseDTO, HttpStatus.CONFLICT);
        }

        // Default response for unhandled exception types
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}