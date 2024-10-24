package br.com.fiap.drighi.api_academica.domain.interfaces;

import br.com.fiap.drighi.api_academica.domain.entity.Avaliacao;
import br.com.fiap.drighi.api_academica.domain.entity.Matricula;

import java.math.BigDecimal;
import java.math.RoundingMode;

@FunctionalInterface
public interface AprovacaoAluno {

    abstract Boolean estaAprovado(
            BigDecimal mediaFinal,
            Integer faltas,
            BigDecimal notaMinima
    );

    default BigDecimal calculaMedia(Matricula matricula) {
        BigDecimal media = BigDecimal.ZERO;
        for (Avaliacao avaliacao : matricula.getAvaliacoes()) {
            media = media.add(avaliacao.getNota());
        }

        // Duas casas decimais e arredondamento para cima
        return media.divide(
                BigDecimal.valueOf(
                        matricula.getAvaliacoes().size()
                ), 2, RoundingMode.UP
        );
    }


}
