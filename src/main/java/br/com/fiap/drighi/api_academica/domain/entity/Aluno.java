package br.com.fiap.drighi.api_academica.domain.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "aluno")
@EqualsAndHashCode(callSuper = true)
public class Aluno extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aluno")
    private List<Matricula> matriculas = new ArrayList<>();

    @Getter
    private String ra;

    @Getter
    private LocalDateTime dataIngresso;

    @Getter
    @Setter
    private LocalDateTime dataFormatura;

    @Getter
    @Setter
    private Boolean isFormado = false;

    @Getter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "aluno_id")
    private List<Contato> contatos = new ArrayList<>();

    @Getter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "aluno_id")
    private Set<Documento> documentos = new HashSet<>();

    @Getter
    @Embedded
    private Endereco endereco;

    @Deprecated
    protected Aluno() {
        super(null,null, null, null);
    }

    public Aluno(
            String primeiroNome,
            String nomeMeio,
            String sobrenome,
            LocalDateTime dataNascimento,
            Endereco endereco,
            Set<Documento> documentos,
            List<Contato> contatos,
            String ra,
            LocalDateTime dataIngresso
    ) {
        super(primeiroNome, nomeMeio, sobrenome, dataNascimento);
        this.contatos = contatos;
        this.ra = ra;
        this.dataIngresso = dataIngresso;
        this.isFormado = false;
    }

    public Aluno(
            String primeiroNome,
            String nomeMeio,
            String sobrenome,
            LocalDateTime dataNascimento,
            Endereco endereco,
            Set<Documento> documentos,
            List<Contato> contatos,
            List<Matricula> matriculas,
            String ra,
            LocalDateTime dataIngresso,
            LocalDateTime dataFormatura
    ) {
        super(primeiroNome, nomeMeio, sobrenome, dataNascimento);
        this.endereco = endereco;
        this.documentos = documentos;
        this.contatos = contatos;
        this.matriculas = matriculas;
        this.ra = ra;
        this.dataIngresso = dataIngresso;
        this.dataFormatura = dataFormatura;
    }

}