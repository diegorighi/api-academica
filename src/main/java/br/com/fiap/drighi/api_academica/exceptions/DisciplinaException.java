package br.com.fiap.drighi.api_academica.exceptions;

import lombok.Getter;

@Getter
public class DisciplinaException extends RuntimeException {

    private final DisciplinaExceptionEnum exceptionType;

    public DisciplinaException(DisciplinaExceptionEnum exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
    }

}
