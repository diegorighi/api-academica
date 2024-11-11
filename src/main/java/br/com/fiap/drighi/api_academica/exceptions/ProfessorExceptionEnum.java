package br.com.fiap.drighi.api_academica.exceptions;

import lombok.Getter;

@Getter
public enum ProfessorExceptionEnum {

    PROFESSOR_JA_EXISTE("Professor já cadastrado"),
    PROFESSOR_NAO_ENCONTRADO("Professor não encontrado");

    private final String mensagem;

    ProfessorExceptionEnum(String mensagem) {
        this.mensagem = mensagem;
    }


}
