package br.com.fiap.drighi.api_academica.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contato")
@EqualsAndHashCode
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Getter @Setter
    @Email(message = "Email inválido.")
    private String email;

    @Getter @Setter
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "Celular deve estar no formato (xx) xxxxx-xxxx.")
    private String celular;

    @Getter @Setter
    private Boolean isPrincipal;

    @Deprecated
    protected Contato() {
        // Exclusivo Hibernate
    }

    public Contato(String email, String celular, Boolean isPrincipal) {
        this.email = email;
        this.celular = celular;
        this.isPrincipal = isPrincipal;
    }

}