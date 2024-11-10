package br.com.fiap.drighi.api_academica.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record InputAlunoDTO(

        @JsonProperty(value = "primeiro_nome", required = true)
        String primeiroNome,
        @JsonProperty(value = "nome_do_meio", required = false)
        String nomeDoMeio,
        @JsonProperty(value = "sobrenome", required = true)
        String sobrenome,
        @JsonProperty(value = "data_nascimento", required = true)
        LocalDateTime dataNascimento,

        @JsonProperty(value = "endereco", required = true)
        EnderecoDTO endereco,

        @JsonProperty(value = "documentos", required = true)
        Set<DocumentoDTO> documentos,

        @JsonProperty(value = "contatos", required = true)
        List<ContatoDTO> contatos,

        @JsonProperty("data_ingresso")
        LocalDateTime dataIngresso,
        @JsonProperty("data_formatura")
        LocalDateTime dataFormatura,
        @JsonProperty("is_formado")
        Boolean isFormado
) {}