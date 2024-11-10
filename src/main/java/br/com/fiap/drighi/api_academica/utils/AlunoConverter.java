package br.com.fiap.drighi.api_academica.utils;

import br.com.fiap.drighi.api_academica.domain.dto.InputAlunoDTO;
import br.com.fiap.drighi.api_academica.domain.entity.Aluno;
import br.com.fiap.drighi.api_academica.domain.entity.Contato;
import br.com.fiap.drighi.api_academica.domain.entity.Documento;
import br.com.fiap.drighi.api_academica.domain.entity.Endereco;
import br.com.fiap.drighi.api_academica.domain.enums.DocumentoEnum;
import br.com.fiap.drighi.api_academica.domain.enums.UfEnum;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.util.stream.Collectors;

public class AlunoConverter implements Converter<InputAlunoDTO, Aluno> {
    private final String ra;

    public AlunoConverter(String ra) {
        this.ra = ra;
    }

    @Override
    public Aluno convert(MappingContext<InputAlunoDTO, Aluno> context) {
        InputAlunoDTO source = context.getSource();
        UfEnum uf = UfEnum.valueOf(source.endereco().getUf());

        return new Aluno(
                source.primeiroNome(),
                source.nomeDoMeio(),
                source.sobrenome(),
                source.dataNascimento(),
                new Endereco(
                        source.endereco().getCep(),
                        source.endereco().getLogradouro(),
                        source.endereco().getNumero(),
                        source.endereco().getComplemento(),
                        source.endereco().getBairro(),
                        source.endereco().getCidade(),
                        uf,
                        source.endereco().getPais())
                ,
                source.documentos().stream().map(
                        doc -> new Documento(
                                DocumentoEnum.valueOf(
                                        doc.getTipo()
                                ),
                                doc.getDataEmissao(),
                                doc.getDataValidade(),
                                doc.getNumero(),
                                doc.getOrgaoEmissor(),
                                doc.getIsPrincipal()
                        )
                ).collect(Collectors.toSet()),
                source.contatos().stream().map(
                        cont -> new Contato(
                                cont.getEmail(),
                                cont.getCelular(),
                                cont.getIsPrincipal()
                        )
                ).collect(Collectors.toList()),
                ra,
                source.dataIngresso()
        );

    }
}
