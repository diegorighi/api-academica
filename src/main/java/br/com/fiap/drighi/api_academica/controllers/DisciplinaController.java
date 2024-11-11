package br.com.fiap.drighi.api_academica.controllers;

import br.com.fiap.drighi.api_academica.domain.dto.DisciplinaDTO;
import br.com.fiap.drighi.api_academica.domain.dto.InputDisciplinaProfessorDTO;
import br.com.fiap.drighi.api_academica.domain.dto.OutputDisciplinaDTO;
import br.com.fiap.drighi.api_academica.services.DisciplinaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/disciplina")
@SuppressWarnings("unused")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @PostMapping
    public ResponseEntity<OutputDisciplinaDTO> inserirDisciplina(@RequestBody DisciplinaDTO disciplinaDTO) {
        log.info("[CONTROLLER] - INICIANDO CADASTRO DE DISCIPLINA");
        OutputDisciplinaDTO wrapResponse =  disciplinaService.inserirDisciplina(disciplinaDTO);
        return ResponseEntity.ok().body(wrapResponse);
    }

    @PatchMapping
    public ResponseEntity<OutputDisciplinaDTO> inserirProfessorDisciplina(
            @RequestBody InputDisciplinaProfessorDTO inputDisciplinaProfessorDTO) {
        log.info("[CONTROLLER] - ATRIBUINDO PROFESSOR A UMA DISCIPLINA EXISTENTE");
        OutputDisciplinaDTO wrapResponse =  disciplinaService
                .inserirProfessorDisciplina(
                        inputDisciplinaProfessorDTO.documentoProfessor(),
                        inputDisciplinaProfessorDTO.nomeDisciplina());
        return ResponseEntity.ok().body(wrapResponse);
    }

}
