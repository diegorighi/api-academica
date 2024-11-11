package br.com.fiap.drighi.api_academica.domain.dto;

import br.com.fiap.drighi.api_academica.domain.enums.DisciplinaEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

public record DisciplinaDTO(

        @JsonProperty("nome")
        DisciplinaEnum nome
) {


}
