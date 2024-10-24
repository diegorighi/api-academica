package br.com.fiap.drighi.api_academica.domain.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "matricula")
@EqualsAndHashCode
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Getter
    @ManyToOne @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    @Getter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matricula")
    private List<FaltaDoAluno> faltas = new LinkedList<FaltaDoAluno>();

    @Getter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matricula")
    private List<Avaliacao> avaliacoes = new LinkedList<Avaliacao>();

    @Getter
    private BigDecimal notaMinima;

    @Getter
    private Integer faltasMaximas = 10;

    @Getter @Setter
    private Boolean isAprovado = false;

    @Deprecated
    protected Matricula() {
        // for JPA
    }

    public Matricula(
            Aluno aluno,
            Disciplina disciplina
    ) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.notaMinima = new BigDecimal(7);
        this.faltasMaximas = 10;
    }

    public void addFalta(FaltaDoAluno falta) {
        this.faltas.add(falta);
    }

    public void addAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
    }

    public List<Avaliacao> getAvaliacoes() {
        return this.avaliacoes;
    }

}
