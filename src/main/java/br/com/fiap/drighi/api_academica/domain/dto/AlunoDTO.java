package br.com.fiap.drighi.api_academica.domain.dto;

import br.com.fiap.drighi.api_academica.domain.enums.UfEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AlunoDTO {

    @JsonProperty(value = "primeiro_nome", required = true)
    private String primeiroNome;

    @JsonProperty(value = "nome_do_meio", required = false)
    private String nomeDoMeio;

    @JsonProperty(value = "sobrenome", required = true)
    private String sobrenome;

    @JsonProperty(value = "data_nascimento", required = true)
    private LocalDateTime dataNascimento;

    // Campos de Endereco
    @JsonProperty(value = "endereco", required = true)
    private EnderecoDTO endereco;

    // Campos de Documento
    @JsonProperty(value = "documentos", required = true)
    private List<DocumentoDTO> documentos;

    // Campos de Contato
    @JsonProperty(value = "contatos", required = true)
    private List<ContatoDTO> contatos;

    @JsonProperty("registro_academico")
    private String ra;

    @JsonProperty("data_ingresso")
    private LocalDateTime dataIngresso;

    @JsonProperty("data_formatura")
    private LocalDateTime dataFormatura;

    @JsonProperty("is_formado")
    private Boolean isFormado;

}