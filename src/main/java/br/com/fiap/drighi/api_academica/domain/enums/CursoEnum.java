package br.com.fiap.drighi.api_academica.domain.enums;

public enum CursoEnum {

    ADS("Análise e Desenvolvimento de Sistemas"),
    SI("Sistemas de Informação"),
    CC("Ciência da Computação"),
    EC("Engenharia da Computação");

    private String descricao;

    CursoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
