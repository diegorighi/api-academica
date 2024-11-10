package br.com.fiap.drighi.api_academica.domain.dto;

public record OutputAlunoSimplificadoDTO(
        String mensagem,
        StringBuilder uri,
        String primeiroNome,
        String nomeDoMeio,
        String sobrenome,
        String ra
) {}
