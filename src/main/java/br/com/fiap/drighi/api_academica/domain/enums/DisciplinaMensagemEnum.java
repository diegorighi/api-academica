package br.com.fiap.drighi.api_academica.domain.enums;

import lombok.Getter;

@Getter
public enum DisciplinaMensagemEnum {

    DISCIPLINA_ADICIONADA("Disciplina inserida com sucesso"),
    PROFESSOR_ATRIBUIDO("Professor atribuído com sucesso: ");

    private final String mensagem;

    DisciplinaMensagemEnum(String mensagem) {
        this.mensagem = mensagem;
    }

}
