package br.com.fiap.drighi.api_academica.domain.entity;

import br.com.fiap.drighi.api_academica.domain.interfaces.Falta;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "falta_professor")
@EqualsAndHashCode
public class FaltaDoProfessor implements Falta<Professor> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Long id;

    private LocalDateTime data;

    @Getter
    @ManyToOne @JoinColumn(name = "professor_id")
    private Professor professor;

    protected FaltaDoProfessor() {
        // for JPA
    }

    public FaltaDoProfessor(Professor professor, LocalDateTime data) {
        this.professor = professor;
        this.data = data;
    }

    @Override
    public LocalDateTime getData() {
        return data;
    }

    @Override
    public Professor getSujeito() { return this.professor; }
}
