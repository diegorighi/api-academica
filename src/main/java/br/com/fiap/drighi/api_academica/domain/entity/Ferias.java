package br.com.fiap.drighi.api_academica.domain.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "ferias")
@EqualsAndHashCode
public class Ferias {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Long id;

    @ManyToOne @JoinColumn(name = "professor_id")
    private Professor professor;

    @Getter @Setter
    private LocalDateTime dataInicio;

    @Getter @Setter
    private LocalDateTime dataFim;

    @Deprecated
    protected Ferias() {
        // for JPA
    }

    public Ferias(
            Professor professor,
            LocalDateTime dataInicio,
            LocalDateTime dataFim
    ) {
        this.professor = professor;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }



}
