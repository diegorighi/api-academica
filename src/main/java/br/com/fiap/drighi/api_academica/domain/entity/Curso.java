package br.com.fiap.drighi.api_academica.domain.entity;

import br.com.fiap.drighi.api_academica.domain.enums.CursoEnum;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "curso")
@EqualsAndHashCode
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Getter
    @Enumerated(EnumType.STRING)
    private CursoEnum nome;

    @Getter @Setter
    private Boolean isAtivo;

    @Getter @Setter
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "curso_disciplina",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private Set<Disciplina> disciplinas = new HashSet<>();

    @Deprecated
    protected Curso() {
        // for JPA
    }

    public Curso(CursoEnum nome, Set<Disciplina> disciplinas, Boolean isAtivo) {
        this.nome = nome;
        this.disciplinas = disciplinas;
        this.isAtivo = isAtivo;
    }
}
