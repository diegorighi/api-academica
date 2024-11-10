package br.com.fiap.drighi.api_academica.exceptions;

import lombok.Getter;

@Getter
public class AlunoException extends RuntimeException {

    private final AlunoExceptionEnum exceptionType;

    public AlunoException(AlunoExceptionEnum exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }

}
