package br.com.fiap.drighi.api_academica.domain.enums;

public enum DocumentoEnum {

    CPF("CPF"),
    PASSAPORTE("Passaporte"),
    CNH("CNH");

    private final String descricao;

    DocumentoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
