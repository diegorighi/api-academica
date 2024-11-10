package br.com.fiap.drighi.api_academica.services;

import br.com.fiap.drighi.api_academica.domain.dto.DocumentoDTO;
import br.com.fiap.drighi.api_academica.domain.dto.InputAlunoDTO;
import br.com.fiap.drighi.api_academica.domain.dto.OutputAlunoSimplificadoDTO;
import br.com.fiap.drighi.api_academica.domain.entity.Aluno;
import br.com.fiap.drighi.api_academica.domain.enums.AlunoMensagemEnum;
import br.com.fiap.drighi.api_academica.domain.enums.DocumentoEnum;
import br.com.fiap.drighi.api_academica.exceptions.AlunoException;
import br.com.fiap.drighi.api_academica.exceptions.AlunoExceptionEnum;
import br.com.fiap.drighi.api_academica.repositories.AlunoRepository;
import br.com.fiap.drighi.api_academica.utils.AlunoConverter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    /**
     * Cria um aluno
     * @param alunoDTO
     * @return Boolean
     */
    public OutputAlunoSimplificadoDTO criarAluno(InputAlunoDTO alunoDTO) {
        log.info("[SERVICE] - INICIO PARA CRIAR UM ALUNO: {}", alunoDTO);
        if (alunoExiste(alunoDTO)) {
            log.error("[SERVICE] - ESTE CPF JA FOI ATRIBUIDO A UM ALUNO");
            throw new AlunoException(AlunoExceptionEnum.ALUNO_JA_EXISTE);
        }
        else {
            log.info("[SERVICE] - CRIANDO OBJETO REFERENTE AO ALUNO");
            var ra = criaRA(alunoDTO);
            var aluno = converteAlunoDTOEmEntity(alunoDTO, ra);

            log.info("[SERVICE] - SALVO NO BANCO DE DADOS");
            alunoRepository.save(aluno);

            return getOutputAlunoSimplificadoDTO(aluno, AlunoMensagemEnum.ALUNO_SALVO);
        }

    }


    /**
     * Retorna um aluno
     * @param ra
     * @return OutputAlunoCompletoDTO
     */
    public OutputAlunoSimplificadoDTO retornaAluno(String ra) {
        var aluno = Optional.ofNullable(alunoRepository.findByRa(ra));

        if (aluno.isPresent()) {
            log.info("[SERVICE] - REGISTRO ACADEMICO ENCONTRADO COM SUCESSO");
            log.info("[SERVICE] - RETORNANDO ALUNO SIMPLIFICADO");
            return getOutputAlunoSimplificadoDTO(aluno.get(), AlunoMensagemEnum.ALUNO_ENCONTRADO);
        } else {
            log.error("[SERVICE] - REGISTRO ACADEMICO NAO ENCONTRADO");
            throw new AlunoException(AlunoExceptionEnum.ALUNO_NAO_ENCONTRADO);
        }
    }



    // METODOS PRIVADOS
    private static OutputAlunoSimplificadoDTO getOutputAlunoSimplificadoDTO(Aluno aluno, AlunoMensagemEnum mensagem) {
        return new OutputAlunoSimplificadoDTO(
                mensagem.getMensagem(),
                new StringBuilder("/aluno/simplificado/").append(aluno.getRa()),
                aluno.getPrimeiroNome(),
                aluno.getNomeMeio(),
                aluno.getSobrenome(),
                aluno.getRa()
        );
    }

    private static Aluno converteAlunoDTOEmEntity(InputAlunoDTO alunoDTO, String ra) {
        log.info("[SERVICE] - CONVERTENDO ALUNO DTO EM ENTITY");
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<InputAlunoDTO, Aluno> typeMap =
                modelMapper.createTypeMap(InputAlunoDTO.class, Aluno.class);

        typeMap.setConverter(new AlunoConverter(ra));

        log.info("[SERVICE] - TENTANDO CONVERTER ALUNO DTO EM ENTITY");
        return modelMapper.map(alunoDTO, Aluno.class);
    }

    private String criaRA(InputAlunoDTO aluno) {
        log.info("[SERVICE] - CRIANDO REGISTRO ACADEMICO PARA ALUNO");
        return "RA" + aluno.dataIngresso().getYear() + UUID.randomUUID().toString().substring(0, 8);
    }

    private Boolean alunoExiste(InputAlunoDTO alunoDTO) {
        log.info("[SERVICE] - VERIFICANDO SE ALUNO JA EXISTE PELO CPF");
        boolean existe = false;
        DocumentoDTO cpf = getDocumentoByTipo(alunoDTO, DocumentoEnum.CPF);

        if (cpf != null) {
            Aluno aluno = alunoRepository.findByDocumentosNumero(cpf.getNumero());
            if (aluno != null) {
                log.error("[SERVICE] - IMPOSSIVEL CADASTRAR O ALUNO");
                existe = true;
            }
        }

        return existe;
    }

    private DocumentoDTO getDocumentoByTipo(InputAlunoDTO alunoDTO, DocumentoEnum tipo) {
        for (DocumentoDTO documentoDTO : alunoDTO.documentos()) {
            if (documentoDTO.getTipo().equals(tipo.getDescricao())) return documentoDTO;
        }
        return null;
    }


}