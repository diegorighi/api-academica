package br.com.fiap.drighi.api_academica.exceptions;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        LocalDateTime dataOcorrenciaErro,
        String mensagem
) {}
