package br.com.fiap.drighi.api_academica.domain.dto;

import br.com.fiap.drighi.api_academica.domain.enums.DocumentoEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DocumentoDTO {

    private String tipo;
    private LocalDateTime dataEmissao;
    private LocalDateTime dataValidade;
    private String numero;
    private String orgaoEmissor;
    private Boolean isPrincipal;

}