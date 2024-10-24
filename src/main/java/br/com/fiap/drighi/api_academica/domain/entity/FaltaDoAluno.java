package br.com.fiap.drighi.api_academica.domain.entity;

import br.com.fiap.drighi.api_academica.domain.interfaces.Falta;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "falta_aluno")
@EqualsAndHashCode
public class FaltaDoAluno implements Falta<Matricula> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Long id;

    private LocalDateTime data;

    @Getter
    @ManyToOne @JoinColumn(name = "matricula_id")
    private Matricula matricula;

    protected FaltaDoAluno() {
        // for JPA
    }

    public FaltaDoAluno(Matricula matricula, LocalDateTime data) {
        this.matricula = matricula;
        this.data = data;
    }

    @Override
    public LocalDateTime getData() {
        return this.data;
    }

    @Override
    public Matricula getSujeito() { return this.matricula; }
}
