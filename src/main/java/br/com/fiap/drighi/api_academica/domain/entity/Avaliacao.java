package br.com.fiap.drighi.api_academica.domain.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "avaliacao")
@EqualsAndHashCode
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Getter @Setter
    private BigDecimal nota;

    @Getter @Setter
    @ManyToOne @JoinColumn(name = "matricula_id")
    private Matricula matricula;

    @Deprecated
    protected Avaliacao() {
        // for JPA
    }

    public Avaliacao(Matricula matricula, BigDecimal nota) {
        this.matricula = matricula;
        this.nota = nota;
    }

    public BigDecimal getNota(){
        return nota;
    }
}
