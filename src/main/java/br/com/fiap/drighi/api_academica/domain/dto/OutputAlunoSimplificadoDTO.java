package br.com.fiap.drighi.api_academica.domain.dto;

public record OutputAlunoSimplificadoDTO(
        StringBuilder mensagem,
        String primeiroNome,
        String nomeDoMeio,
        String sobrenome,
        String ra
) {}
