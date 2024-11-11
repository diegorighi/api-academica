package br.com.fiap.drighi.api_academica.domain.enums;

import lombok.Getter;

@Getter
public enum AlunoMensagemEnum {

    ALUNO_ENCONTRADO("Aluno encontrado com sucesso"),
    ALUNO_SALVO("Aluno salvo com sucesso");

    private final String mensagem;

    AlunoMensagemEnum(String mensagem) {
        this.mensagem = mensagem;
    }


}
