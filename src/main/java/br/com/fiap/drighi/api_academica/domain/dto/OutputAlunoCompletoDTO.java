package br.com.fiap.drighi.api_academica.domain.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record OutputAlunoCompletoDTO(
        String ra,

        String primeiroNome,
        String nomeDoMeio,
        String sobrenome,
        LocalDateTime dataNascimento,

        EnderecoDTO endereco,
        Set<DocumentoDTO> documentos,
        List<ContatoDTO> contatos,

        List<MatriculaDTO> matriculas,

        LocalDateTime dataIngresso,
        LocalDateTime dataFormatura,
        Boolean isFormado

) {
}
