package br.com.fiap.drighi.api_academica.domain.dto;

import java.util.Set;

public record CursoDTO(
    String nome,
    Boolean isAtivo,
    Set<DisciplinaDTO> disciplinas
) {}
