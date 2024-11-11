package br.com.fiap.drighi.api_academica.exceptions;

import lombok.Getter;

@Getter
public enum DisciplinaExceptionEnum {

    DISCIPLINA_JA_EXISTE("Disciplina já cadastrada"),
    DISCIPLINA_NAO_ENCONTRADA("Disciplina não encontrada");

    private final String message;

    DisciplinaExceptionEnum(String message) {
        this.message = message;
    }


}
