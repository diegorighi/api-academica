package br.com.fiap.drighi.api_academica.services;

import br.com.fiap.drighi.api_academica.domain.dto.DisciplinaDTO;
import br.com.fiap.drighi.api_academica.domain.dto.OutputDisciplinaDTO;
import br.com.fiap.drighi.api_academica.domain.entity.Disciplina;
import br.com.fiap.drighi.api_academica.repositories.DisciplinaRepository;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public OutputDisciplinaDTO inserirDisciplina(DisciplinaDTO disciplinaDTO) {

        // VERIFICA SE A DISCIPLINA JÁ EXISTE
        Disciplina disciplina = disciplinaRepository.findByNome(disciplinaDTO.nome());

        if(disciplina == null) disciplinaRepository.save(dtoToEntity(disciplinaDTO));
        else throw new DisciplinaException("Disciplina já cadastrada");

        return null;

    }

    private Disciplina dtoToEntity(DisciplinaDTO disciplinaDTO) {
        return new Disciplina(disciplinaDTO.nome());
    }
}
