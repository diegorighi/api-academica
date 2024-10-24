package br.com.fiap.drighi.api_academica.domain.entity;

import br.com.fiap.drighi.api_academica.domain.enums.CursoEnum;
import br.com.fiap.drighi.api_academica.domain.enums.DisciplinaEnum;
import br.com.fiap.drighi.api_academica.domain.enums.DocumentoEnum;
import br.com.fiap.drighi.api_academica.domain.enums.UfEnum;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class ProfessorTest {

    @Test
    public void criaProfessor() {
        Professor professor = new Professor(
                "John",
                "Doe",
                "Smith",
                LocalDateTime.of(1980, 1, 1, 0, 0),
                new Endereco("12345-678", "Rua A", "123", "Apto 1", "Bairro B", "Cidade C", UfEnum.SP, "Brasil"),
                Arrays.asList(new Documento(DocumentoEnum.CPF, LocalDateTime.of(1980, 1, 1, 0, 0), LocalDateTime.of(2023, 1, 1, 0, 0), "123.456.789-00", "SSP", true), new Documento(DocumentoEnum.CNH, LocalDateTime.of(1980, 1, 1, 0, 0), LocalDateTime.of(2023, 1, 1, 0, 0), "123456789", "SSP", false)),
                Arrays.asList(new Contato("jhon@email.com", "(11) 99999-9999", true), new Contato("doe@mailer.com.br", "(11) 99999-9998", false)),
                "12345",
                true);

        assertNotNull(professor);
        assertEquals("12345", professor.getRegistro());
        assertTrue(professor.getIsAtivo());
        assertNotNull(professor.getDisciplinas());
        assertNotNull(professor.getFaltas());
        assertNotNull(professor.getSalarios());
        assertNotNull(professor.getFerias());
    }

    @Test
    public void criaProfessorCompletoComDadosFicticios() {
        // Initialize Professor
        Professor professor = new Professor(
                "John",
                "Doe",
                "Smith",
                LocalDateTime.of(1980, 1, 1, 0, 0),
                new Endereco("12345-678", "Rua A", "123", "Apto 1", "Bairro B", "Cidade C", UfEnum.SP, "Brasil"),
                Arrays.asList(new Documento(DocumentoEnum.CPF, LocalDateTime.of(1980, 1, 1, 0, 0), LocalDateTime.of(2023, 1, 1, 0, 0), "123.456.789-00", "SSP", true), new Documento(DocumentoEnum.PASSAPORTE, LocalDateTime.of(1980, 1, 1, 0, 0), LocalDateTime.of(2023, 1, 1, 0, 0), "123456789", "SSP", false)),
                Arrays.asList(new Contato("jhon@email.com", "(11) 99999-9999", true), new Contato("doe@mailer.com.br", "(11) 99999-9998", false)),
                "12345",
                true
        );

        // Initialize Curso
        Curso curso = new Curso(CursoEnum.ADS, new HashSet<>(), true);

        // Initialize Disciplina
        Disciplina disciplina = new Disciplina(professor, DisciplinaEnum.MATEMATICA_DISCRETA, new HashSet<>());

        // Initialize FaltaDoProfessor
        FaltaDoProfessor falta = new FaltaDoProfessor(professor, LocalDateTime.of(2023, 10, 1, 0, 0));

        // Initialize Salario
        Salario salario = new Salario(professor, new BigDecimal("5000.00"), LocalDateTime.of(2023, 1, 1, 0, 0), LocalDateTime.of(2023, 12, 31, 23, 59));

        // Initialize Ferias
        Ferias ferias = new Ferias(professor, LocalDateTime.of(2023, 12, 1, 0, 0), LocalDateTime.of(2024, 1, 1, 0, 0));

        // Add initialized objects to Professor
        professor.addDisciplina(disciplina);
        professor.addFalta(falta);
        professor.atualizarSalario(salario);
        professor.addFerias(ferias);

        // Assertions
        assertNotNull(professor);
        assertEquals("12345", professor.getRegistro());
        assertTrue(professor.getIsAtivo());
        assertEquals(1, professor.getDisciplinas().size());
        assertEquals(1, professor.getFaltas().size());
        assertEquals(1, professor.getSalarios().size());
        assertEquals(1, professor.getFerias().size());

        // Additional assertions to verify the data
        assertEquals(DisciplinaEnum.MATEMATICA_DISCRETA, professor.getDisciplinas().get(0).getNome());
        assertEquals(LocalDateTime.of(2023, 10, 1, 0, 0), professor.getFaltas().get(0).getData());
        assertEquals(new BigDecimal("5000.00"), professor.getSalarios().get(0).getValor());
        assertEquals(LocalDateTime.of(2023, 12, 1, 0, 0), professor.getFerias().get(0).getDataInicio());
        assertEquals(LocalDateTime.of(2024, 1, 1, 0, 0), professor.getFerias().get(0).getDataFim());
    }

}
