package br.com.fiap.drighi.api_academica.domain.entity;

import br.com.fiap.drighi.api_academica.domain.enums.DisciplinaEnum;
import br.com.fiap.drighi.api_academica.domain.enums.DocumentoEnum;
import br.com.fiap.drighi.api_academica.domain.enums.UfEnum;
import br.com.fiap.drighi.api_academica.domain.interfaces.AprovacaoAluno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MatriculaTest {

    private Endereco endereco;
    private Set<Documento> documentos;
    private List<Contato> contatos;
    private List<Matricula> matriculas;
    private Avaliacao avaliacao1;
    private Avaliacao avaliacao2;
    private Avaliacao avaliacao3;

    @BeforeEach
    public void setUp() {
        endereco = new Endereco(
                "04436-020",
                "Rua dos Bobos",
                "Sem Número",
                "CASA 1",
                "Jardim das Flores",
                "São Paulo",
                UfEnum.SP,
                "Brasil"
        );

        documentos = new HashSet<>();
        documentos.add(new Documento(
                DocumentoEnum.CPF,
                LocalDateTime.of(2000, 1, 1, 0, 0),
                LocalDateTime.of(2030, 1, 1, 0, 0),
                "123.456.789-00",
                "SSP",
                true
        ));

        contatos = new ArrayList<>();
        contatos.add(new Contato(
                "joao@email.com",
                "(11) 99999-9999",
                true
        ));

        Aluno aluno = new Aluno(
                "João",
                "Pedro",
                "Silva",
                LocalDateTime.of(1988, 4, 13, 0, 0),
                endereco,
                documentos,
                contatos,
                "123456",
                LocalDateTime.of(2018, 1, 1, 0, 0)
        );

        Professor professor = new Professor(
                "Carlos",
                "Eduardo",
                "Santos",
                LocalDateTime.of(1975, 5, 20, 0, 0),
                endereco,
                documentos,
                contatos,
                "98765",
                true
        );

        Disciplina disciplina = new Disciplina(
                professor,
                DisciplinaEnum.ALGORITMOS,
                new HashSet<>()
        );

        Matricula matricula = new Matricula(aluno, disciplina);
        matriculas = new ArrayList<>();
        matriculas.add(matricula);

        // Adiciona avaliações
        avaliacao1 = new Avaliacao(
                matricula,
                new BigDecimal("8.00")
        );
        avaliacao2 = new Avaliacao(
                matricula,
                new BigDecimal("7.00")
        );
        avaliacao3 = new Avaliacao(
                matricula,
                new BigDecimal("9.00")
        );

        matricula.addAvaliacao(avaliacao1);
        matricula.addAvaliacao(avaliacao2);
        matricula.addAvaliacao(avaliacao3);

    }


    @Test
    @DisplayName("Cria um aluno com todos os campos preenchidos, incluindo a lista de matrículas")
    public void criaAlunoCompletoComMatriculas() {
        Aluno aluno = new Aluno(
                "João",
                "Pedro",
                "Silva",
                LocalDateTime.of(1988, 4, 13, 0, 0),
                endereco,
                documentos,
                contatos,
                matriculas,
                "123456",
                LocalDateTime.of(2018, 1, 1, 0, 0),
                LocalDateTime.of(2022, 12, 31, 0, 0)
        );

        // Assertions
        assertNotNull(aluno);
        assertEquals("João", aluno.getPrimeiroNome());
        assertEquals("Pedro", aluno.getNomeMeio());
        assertEquals("Silva", aluno.getSobrenome());
        assertEquals(LocalDateTime.of(1988, 4, 13, 0, 0), aluno.getDataNascimento());
        assertEquals(endereco, aluno.getEndereco());

        // Documentos é um SET. Uma iteração foi feita para comparar os elementos
        assertEquals(documentos.size(), aluno.getDocumentos().size());
        for(Documento documento : documentos) {
            assertTrue(aluno.getDocumentos().contains(documento));
        }

        // Contatos é um List herdada classe Pessoa. Uma iteração foi feita para comparar os elementos
        assertEquals(contatos.size(), aluno.getContatos().size());

        for(Contato contato : contatos) {
            assertTrue(aluno.getContatos().contains(contato));
        }

        assertEquals(matriculas, aluno.getMatriculas());
        assertEquals("123456", aluno.getRa());
        assertEquals(LocalDateTime.of(2018, 1, 1, 0, 0), aluno.getDataIngresso());
        assertEquals(LocalDateTime.of(2022, 12, 31, 0, 0), aluno.getDataFormatura());
        assertFalse(aluno.getIsFormado());
    }

    @Test
    @DisplayName("Testa a aprovação do aluno com base na média e faltas")
    public void testaAprovacaoAluno() {
        AprovacaoAluno aprovacaoAluno =
                (mediaFinal, faltas, notaMinima) ->
                mediaFinal.compareTo(notaMinima) >= 0 && faltas <= 10;

        // Testa UMA matricula
        Matricula matricula = matriculas.get(0);

        BigDecimal media = aprovacaoAluno.calculaMedia(matricula);
        Boolean isAprovado = aprovacaoAluno.estaAprovado(media, 5, new BigDecimal("7"));

        assertNotNull(media);
        assertEquals(new BigDecimal("8.00"), media);
        assertTrue(isAprovado);
    }

}