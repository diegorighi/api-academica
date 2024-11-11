package br.com.fiap.drighi.api_academica.controllers;

import br.com.fiap.drighi.api_academica.domain.dto.DisciplinaDTO;
import br.com.fiap.drighi.api_academica.domain.dto.OutputDisciplinaDTO;
import br.com.fiap.drighi.api_academica.services.DisciplinaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @PostMapping
    public ResponseEntity<OutputDisciplinaDTO> inserirDisciplina(@RequestBody @Valid DisciplinaDTO disciplinaDTO) {
        log.info("[CONTROLLER] - INICIANDO CADASTRO DE DISCIPLINA");

        OutputDisciplinaDTO wrapResponse =  disciplinaService.inserirDisciplina(disciplinaDTO);

        return null;
    }

}
