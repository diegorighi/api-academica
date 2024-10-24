package br.com.fiap.drighi.api_academica.domain.entity;

import br.com.fiap.drighi.api_academica.domain.enums.UfEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
public class Endereco {

    @Getter @Setter
    @Pattern(regexp = "^[0-9]{5}-[0-9]{3}$", message = "CEP inválido. O formato deve ser XXXXX-XXX.")
    private String cep;

    @Getter @Setter
    private String logradouro;

    @Getter @Setter
    private String numero;

    @Getter @Setter
    private String complemento;

    @Getter @Setter
    private String bairro;

    @Getter @Setter
    private String cidade;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private UfEnum uf;

    @Getter @Setter
    private String pais;

    @Deprecated
    public Endereco() {
        // Exclusivo para uso do Hibernate
    }

    public Endereco(
            String cep,
            String logradouro,
            String numero,
            String complemento,
            String bairro,
            String cidade,
            UfEnum uf,
            String pais
    ) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.pais = pais;
    }


}
