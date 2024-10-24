package br.com.fiap.drighi.api_academica.domain.entity;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
@EqualsAndHashCode
public class Salario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @Getter @Setter
    private BigDecimal valor;

    @Getter @Setter
    private LocalDateTime dataInicio;

    @Getter @Setter
    private LocalDateTime dataFim;

    @Deprecated
    protected Salario() {
        // for JPA
    }

    public Salario(
            Professor professor,
            BigDecimal valor,
            LocalDateTime dataInicio,
            LocalDateTime dataFim
    ) {
        this.professor = professor;
        this.valor = valor;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

}
