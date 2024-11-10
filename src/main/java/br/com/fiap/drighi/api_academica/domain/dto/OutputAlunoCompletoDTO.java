package br.com.fiap.drighi.api_academica.domain.dto;

import java.util.List;
import java.util.Set;

public record OutputAlunoCompletoDTO(
        String primeiroNome,
        String nomeDoMeio,
        String sobrenome,
        String ra,

        EnderecoDTO endereco,
        Set<DocumentoDTO> documentos,
        List<ContatoDTO> contatos

        //List<MatriculaDTO> matriculas
) {
}
