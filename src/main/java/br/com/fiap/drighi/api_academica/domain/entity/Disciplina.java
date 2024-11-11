package br.com.fiap.drighi.api_academica.domain.entity;

import br.com.fiap.drighi.api_academica.domain.enums.DisciplinaEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "disciplina")
@EqualsAndHashCode
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Getter
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @Getter
    @Enumerated(EnumType.STRING)
    private DisciplinaEnum nome;

    @Getter
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "disciplinas")
    private Set<Curso> cursos = new HashSet<>();

    @Deprecated
    protected Disciplina() {
        // for JPA
    }

    public Disciplina(Professor professor, DisciplinaEnum nome, Set<Curso> cursos) {
        this.professor = professor;
        this.nome = nome;
        this.cursos = cursos;
    }

    public Disciplina(DisciplinaEnum nome) {
        this.nome = nome;
    }

    public void addCurso(Curso curso) {
        this.cursos.add(curso);
    }

    public void removeCurso(Curso curso) {
        this.cursos.remove(curso);
    }


}
