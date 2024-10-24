package br.com.fiap.drighi.api_academica.domain.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "professor")
@EqualsAndHashCode
public class Professor extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Getter
    private String registro;

    @Getter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
    private List<Disciplina> disciplinas = new LinkedList<Disciplina>();

    @Getter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
    private List<FaltaDoProfessor> faltas = new LinkedList<FaltaDoProfessor>();

    @Getter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
    private List<Salario> salarios = new LinkedList<Salario>();

    @Getter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
    private List<Ferias> ferias = new LinkedList<Ferias>();

    @Getter
    @Embedded
    private Endereco endereco;

    @Getter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "professor_id") // Cria a FK em Contato
    private List<Contato> contatos;

    @Getter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "professor_id") // Cria a FK em Documento
    private Set<Documento> documentos;



    @Getter
    private Boolean isAtivo;

    @Deprecated
    protected Professor() {
        super(null,null, null, null);
        // for JPA
    }

    public Professor(
            String primeiroNome,
            String nomeMeio,
            String sobrenome,
            LocalDateTime dataNascimento,
            Endereco endereco,
            Set<Documento> documentos,
            List<Contato> contatos,
            String registro,
            Boolean isAtivo
    ) {
        super(primeiroNome, nomeMeio, sobrenome, dataNascimento);
        this.endereco = endereco;
        this.documentos = documentos;
        this.contatos = contatos;
        this.registro = registro;
        this.isAtivo = isAtivo;
    }

    public Professor(
        String primeiroNome,
        String nomeMeio,
        String sobrenome,
        LocalDateTime dataNascimento,
        Endereco endereco,
        Set<Documento> documentos,
        List<Contato> contatos,
        String registro,
        List<Disciplina> disciplinas,
        List<FaltaDoProfessor> faltas,
        List<Salario> salarios,
        List<Ferias> ferias,
        Boolean isAtivo
    ){
        super(primeiroNome, nomeMeio, sobrenome, dataNascimento);
        this.registro = registro;
        this.disciplinas = disciplinas;
        this.faltas = faltas;
        this.salarios = salarios;
        this.ferias = ferias;
        this.endereco = endereco;
        this.documentos = documentos;
        this.contatos = contatos;
        this.isAtivo = isAtivo;
    }

    public void addDisciplina(Disciplina disciplina) {
        this.disciplinas.add(disciplina);
    }

    public void addFalta(FaltaDoProfessor falta) {
        this.faltas.add(falta);
    }

    public void atualizarSalario(Salario salario) {
        this.salarios.add(salario);
    }

    public void addFerias(Ferias ferias) {
        this.ferias.add(ferias);
    }

    public void setAtivo(Boolean isAtivo) {
        this.isAtivo = isAtivo;
    }

}