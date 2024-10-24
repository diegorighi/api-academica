package br.com.fiap.drighi.api_academica.domain.entity;

import br.com.fiap.drighi.api_academica.domain.enums.UfEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EnderecoTest {

    @Test
    public void criaEndereco() {
        Endereco endereco = new Endereco(
                "04436-020",
                "Rua dos Bobos",
                "Sem Número",
                "CASA 1",
                "Jardim das Flores",
                "São Paulo",
                UfEnum.SP,
                "Brasil"
        );

        assertNotNull(endereco);
        assertEquals("04436-020", endereco.getCep());
        assertEquals("Rua dos Bobos", endereco.getLogradouro());
        assertEquals("Sem Número", endereco.getNumero());
        assertEquals("CASA 1", endereco.getComplemento());
        assertEquals("Jardim das Flores", endereco.getBairro());
        assertEquals("São Paulo", endereco.getCidade());
        assertEquals(UfEnum.SP, endereco.getUf());
        assertEquals("Brasil", endereco.getPais());
    }
}
