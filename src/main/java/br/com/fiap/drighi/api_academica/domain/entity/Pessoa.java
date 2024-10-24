package br.com.fiap.drighi.api_academica.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@MappedSuperclass
public abstract class Pessoa {

    private String primeiroNome;
    private String nomeMeio;
    private String sobrenome;
    private LocalDateTime dataNascimento;

    public Pessoa(
            String primeiroNome,
            String nomeMeio,
            String sobrenome,
            LocalDateTime dataNascimento
    ) {
        this.primeiroNome = primeiroNome;
        this.nomeMeio = nomeMeio;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
    }

}
