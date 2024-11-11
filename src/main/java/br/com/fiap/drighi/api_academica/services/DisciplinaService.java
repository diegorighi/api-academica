package br.com.fiap.drighi.api_academica.services;

import br.com.fiap.drighi.api_academica.domain.dto.DisciplinaDTO;
import br.com.fiap.drighi.api_academica.domain.dto.OutputDisciplinaDTO;
import br.com.fiap.drighi.api_academica.domain.entity.Disciplina;
import br.com.fiap.drighi.api_academica.domain.enums.DisciplinaEnum;
import br.com.fiap.drighi.api_academica.domain.enums.DisciplinaMensagemEnum;
import br.com.fiap.drighi.api_academica.exceptions.DisciplinaException;
import br.com.fiap.drighi.api_academica.exceptions.DisciplinaExceptionEnum;
import br.com.fiap.drighi.api_academica.exceptions.ProfessorException;
import br.com.fiap.drighi.api_academica.exceptions.ProfessorExceptionEnum;
import br.com.fiap.drighi.api_academica.repositories.DisciplinaRepository;
import br.com.fiap.drighi.api_academica.repositories.ProfessorRepository;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final ProfessorRepository professorRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository, ProfessorRepository professorRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
    }

    public OutputDisciplinaDTO inserirDisciplina(DisciplinaDTO disciplinaDTO) {

        Disciplina disciplina = disciplinaRepository.findByNome(disciplinaDTO.nome());

        if(disciplina == null) {
            var disciplinaSalva = disciplinaRepository.save(dtoToEntity(disciplinaDTO));
            return new OutputDisciplinaDTO(
                    DisciplinaMensagemEnum.DISCIPLINA_ADICIONADA.getMensagem(),
                    disciplinaSalva.getNome().getDescricao()
            );
        }
        else throw new DisciplinaException(DisciplinaExceptionEnum.DISCIPLINA_JA_EXISTE);

    }

    public OutputDisciplinaDTO inserirProfessorDisciplina(String documento, DisciplinaEnum nomeDisciplina) {
        Disciplina disciplina = disciplinaRepository.findByNome(nomeDisciplina);

        // VERIRICA SE A DISCIPLINA ESTÁ CADASTRADA
        if(disciplina == null) {
            throw new DisciplinaException(DisciplinaExceptionEnum.DISCIPLINA_NAO_ENCONTRADA);
        }
        // VERIRICA SE O PROFESSOR EXISTE NO BANCO
        var professorEntity = professorRepository.findByDocumentosNumero(documento);
        if(professorEntity == null) {
            throw new ProfessorException(ProfessorExceptionEnum.PROFESSOR_NAO_ENCONTRADO);
        }


        disciplina.setProfessor(professorEntity);
        String nomeCompleto = professorEntity.getPrimeiroNome() + " " + professorEntity.getSobrenome();
        disciplinaRepository.save(disciplina);
        return new OutputDisciplinaDTO(
                DisciplinaMensagemEnum.PROFESSOR_ATRIBUIDO.getMensagem()+nomeCompleto,
                disciplina.getNome().getDescricao()
        );

    }


    private Disciplina dtoToEntity(DisciplinaDTO disciplinaDTO) {
        return new Disciplina(disciplinaDTO.nome());
    }
}
