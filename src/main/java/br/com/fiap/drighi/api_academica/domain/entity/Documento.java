package br.com.fiap.drighi.api_academica.domain.entity;

import br.com.fiap.drighi.api_academica.domain.enums.DocumentoEnum;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "documento")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Getter @Setter
    private DocumentoEnum tipo;

    @Getter @Setter
    private LocalDateTime dataEmissao;

    @Getter @Setter
    private LocalDateTime dataValidade;

    @Getter @Setter
    private String numero;

    @Getter @Setter
    private String orgaoEmissor;

    @Getter @Setter
    private Boolean isPrincipal;


    @Deprecated
    protected Documento() {
        // Exclusivo Hibernate
    }

    public Documento(
            DocumentoEnum tipo,
            LocalDateTime dataEmissao,
            LocalDateTime dataValidade,
            String numero,
            String orgaoEmissor,
            Boolean isPrincipal
    ) {
        this.tipo = tipo;
        this.dataEmissao = dataEmissao;
        this.dataValidade = dataValidade;
        this.numero = numero;
        this.orgaoEmissor = orgaoEmissor;
        this.isPrincipal = isPrincipal;
    }


}
