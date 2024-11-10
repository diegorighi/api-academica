package br.com.fiap.drighi.api_academica.services;

import br.com.fiap.drighi.api_academica.domain.dto.DocumentoDTO;
import br.com.fiap.drighi.api_academica.domain.dto.InputAlunoDTO;
import br.com.fiap.drighi.api_academica.domain.entity.Aluno;
import br.com.fiap.drighi.api_academica.domain.enums.DocumentoEnum;
import br.com.fiap.drighi.api_academica.repositories.AlunoRepository;
import br.com.fiap.drighi.api_academica.utils.AlunoConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Boolean criarAluno(InputAlunoDTO alunoDTO) {
        if (!alunoExiste(alunoDTO)) {
            String ra = criaRA(alunoDTO);

            ModelMapper modelMapper = new ModelMapper();
            TypeMap<InputAlunoDTO, Aluno> typeMap =
                    modelMapper.createTypeMap(InputAlunoDTO.class, Aluno.class);

            typeMap.setConverter(new AlunoConverter(ra));

            Aluno aluno = modelMapper.map(alunoDTO, Aluno.class);
            alunoRepository.save(aluno);

            return true;
        }
        return false;
    }

    // METODOS PRIVADOS

    private String criaRA(InputAlunoDTO aluno) {
        return "RA" + aluno.dataIngresso().getYear() + UUID.randomUUID().toString().substring(0, 8);
    }

    private Boolean alunoExiste(InputAlunoDTO alunoDTO) {
        boolean existe = false;
        DocumentoDTO cpf = getDocumentoByTipo(alunoDTO, DocumentoEnum.CPF);

        if (cpf != null) {
            Aluno aluno = alunoRepository.findByDocumentosNumero(cpf.getNumero());
            if (aluno != null) {
                existe = true;
            }
        }

        return existe;
    }

    private DocumentoDTO getDocumentoByTipo(InputAlunoDTO alunoDTO, DocumentoEnum tipo) {
        for (DocumentoDTO documentoDTO : alunoDTO.documentos()) {
            if (documentoDTO.getTipo().equals(tipo.getDescricao())) {
                return documentoDTO;
            }
        }
        return null;
    }


}