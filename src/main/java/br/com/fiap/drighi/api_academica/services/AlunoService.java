package br.com.fiap.drighi.api_academica.services;

import br.com.fiap.drighi.api_academica.domain.dto.AlunoDTO;
import br.com.fiap.drighi.api_academica.domain.dto.DocumentoDTO;
import br.com.fiap.drighi.api_academica.domain.entity.Aluno;
import br.com.fiap.drighi.api_academica.domain.enums.DocumentoEnum;
import br.com.fiap.drighi.api_academica.repositories.AlunoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AlunoService {

    private AlunoRepository alunoRepository;

    public AlunoService(@Autowired AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public void criaRA(AlunoDTO alunoDTO) {
        alunoDTO.setRa(
                "RA"
                        + alunoDTO.getDataIngresso().getYear()
                        + UUID.randomUUID().toString().substring(0, 8)
        );
    }

    public Boolean alunoExiste(AlunoDTO alunoDTO) {
        boolean existe = false;
        DocumentoDTO cpf = getDocumentoByTipo(alunoDTO, DocumentoEnum.CPF);

        if(cpf != null) {
            Aluno aluno = alunoRepository.findByDocumentosNumero(cpf.getNumero());
            if(aluno != null) {
                existe = true;
            }
        }

        return existe;
    }

    private DocumentoDTO getDocumentoByTipo(AlunoDTO alunoDTO, DocumentoEnum tipo) {
       for(DocumentoDTO documentoDTO : alunoDTO.getDocumentos()) {
           if(documentoDTO.getTipo().equals(tipo.getDescricao())) {
               return documentoDTO;
           }
       }
       return null;
    }

    public Aluno criarAluno(AlunoDTO alunoDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Aluno aluno = modelMapper.map(alunoDTO, Aluno.class);
        alunoRepository.save(aluno);
        return aluno;
    }

}
