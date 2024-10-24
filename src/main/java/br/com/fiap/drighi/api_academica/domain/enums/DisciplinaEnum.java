package br.com.fiap.drighi.api_academica.domain.enums;

public enum DisciplinaEnum {

    CALC_1("Cálculo 1"),
    CALC_2("Cálculo 2"),
    ENG_SOFTWARE("Engenharia de Software"),
    MATEMATICA_DISCRETA("Matemática Discreta"),
    ESTRUTURA_DADOS("Estrutura de Dados"),
    ALGORITMOS("Algoritmos"),
    REDES_COMPUTADORES("Redes de Computadores"),
    BANCO_DADOS("Banco de Dados"),
    INTELIGENCIA_ARTIFICIAL("Inteligência Artificial");

    private String descricao;

    DisciplinaEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
