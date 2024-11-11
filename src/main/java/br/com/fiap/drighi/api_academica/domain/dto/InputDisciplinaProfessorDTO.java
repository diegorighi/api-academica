package br.com.fiap.drighi.api_academica.domain.dto;

import br.com.fiap.drighi.api_academica.domain.enums.DisciplinaEnum;

public record InputDisciplinaProfessorDTO(
        String documentoProfessor,
        DisciplinaEnum nomeDisciplina
) {
}
