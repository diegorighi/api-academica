package br.com.fiap.drighi.api_academica.exceptions;

import lombok.Getter;

@Getter
public class ProfessorException extends RuntimeException {

    private final ProfessorExceptionEnum exceptionType;

    public ProfessorException(ProfessorExceptionEnum exceptionType) {
        super(exceptionType.getMensagem());
        this.exceptionType = exceptionType;
    }
}
