package br.com.fiap.drighi.api_academica.domain.entity;

import br.com.fiap.drighi.api_academica.domain.enums.DocumentoEnum;
import br.com.fiap.drighi.api_academica.domain.enums.UfEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlunoTest {

    // Cria instâncias estáticas para usar nos testes com BeforeEach
    private static Aluno aluno;
    private static Endereco endereco;
    private static Set<Documento> documentos;
    private static List<Contato> contatos;

    @BeforeEach
    public void criaInstancias() {

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

        documentos = new HashSet<Documento>();
        documentos.add(
                new Documento(
                        DocumentoEnum.CPF,
                        LocalDateTime.of(2000, 1, 1, 0, 0),
                        LocalDateTime.of(2030, 1, 1, 0, 0),
                        "123.456.789-00",
                        "SSP",
                        true
                )
        );

        contatos = new ArrayList<Contato>();
        contatos.add(
                new Contato(
                        "joao@email.com",
                        "(11) 99999-9999",
                        true)
        );

        aluno = new Aluno(
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
    }


    @Test
    @DisplayName("Cria um aluno do zero")
    public void criaAlunoDoZero() {

        // Testa se o aluno foi criado corretamente
        assert aluno.getPrimeiroNome().equals("João");
        assert aluno.getNomeMeio().equals("Pedro");
        assert aluno.getSobrenome().equals("Silva");
        assert aluno.getDataNascimento().equals(LocalDateTime.of(1988, 4, 13, 0, 0));
        assert aluno.getEndereco().getCep().equals("04436-020");
        assert aluno.getEndereco().getLogradouro().equals("Rua dos Bobos");
        assert aluno.getEndereco().getNumero().equals("Sem Número");
        assert aluno.getEndereco().getComplemento().equals("CASA 1");
        assert aluno.getEndereco().getBairro().equals("Jardim das Flores");
        assert aluno.getEndereco().getCidade().equals("São Paulo");
        assert aluno.getEndereco().getUf().equals(UfEnum.SP);
        assert aluno.getEndereco().getPais().equals("Brasil");

        aluno.getDocumentos().forEach(
                documento -> {
                    assert documento.getTipo().equals(DocumentoEnum.CPF);
                    assert documento.getDataEmissao().equals(LocalDateTime.of(2000, 1, 1, 0, 0));
                    assert documento.getDataValidade().equals(LocalDateTime.of(2030, 1, 1, 0, 0));
                    assert documento.getNumero().equals("123.456.789-00");
                    assert documento.getOrgaoEmissor().equals("SSP");
                    assert documento.getIsPrincipal().equals(true);
                }
        );

        aluno.getContatos().forEach(
                contato -> {
                    assert contato.getEmail().equals("joao@email.com");
                    assert contato.getCelular().equals("(11) 99999-9999");
                    assert contato.getIsPrincipal().equals(true);
                }
        );

    }
}
