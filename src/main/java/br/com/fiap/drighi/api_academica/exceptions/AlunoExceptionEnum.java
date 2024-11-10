package br.com.fiap.drighi.api_academica.exceptions;

import lombok.Getter;

@Getter
public enum AlunoExceptionEnum {

    ALUNO_NAO_ENCONTRADO("Aluno não encontrado"),
    ALUNO_JA_EXISTE("Aluno já existe");

    private final String message;

    AlunoExceptionEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
