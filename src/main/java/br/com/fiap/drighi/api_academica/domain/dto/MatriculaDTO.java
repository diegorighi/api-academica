package br.com.fiap.drighi.api_academica.domain.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDTO {

    private DisciplinaDTO disciplina;
    private FaltaDoAlunoDTO faltas;
    private List<AvaliacaoDTO> avaliacoes = new ArrayList<AvaliacaoDTO>();

    private BigDecimal notaMinima;
    private Integer faltasMaximas;
    private Boolean isAprovado;

}
