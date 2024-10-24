package br.com.fiap.drighi.api_academica.domain.dto;

import lombok.Data;

@Data
public class ContatoDTO {

    private String email;
    private String celular;
    private Boolean isPrincipal;

}