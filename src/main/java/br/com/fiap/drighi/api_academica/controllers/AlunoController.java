package br.com.fiap.drighi.api_academica.controllers;

import br.com.fiap.drighi.api_academica.domain.dto.InputAlunoDTO;
import br.com.fiap.drighi.api_academica.domain.dto.OutputAlunoSimplificadoDTO;
import br.com.fiap.drighi.api_academica.services.AlunoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/aluno")
@SuppressWarnings("unused")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<OutputAlunoSimplificadoDTO> criarAluno(@RequestBody @Valid InputAlunoDTO alunoDTO) {
        log.info("[CONTROLLER] - INICIO PARA CRIAR UM ALUNO");
        OutputAlunoSimplificadoDTO aluno = alunoService.criarAluno(alunoDTO);

        // SE O RA É DIFERENTE DE NULL, SIGNIFICA QUE O ALUNO FOI CRIADO COM SUCESSO
        if(aluno.ra() != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{ra}")
                    .buildAndExpand(aluno.ra())
                    .toUri();

            return ResponseEntity.created(location).body(aluno);
        }

        // CASO CONTRÁRIO, RETORNA BAD REQUEST
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/simplificado/{ra}")
    public ResponseEntity<OutputAlunoSimplificadoDTO> buscarAlunoPorRA(@PathVariable String ra) {
        log.info("[CONTROLLER] - INICIO PARA BUSCAR ALUNO SIMPLIFICADO PELO REGISTRO ACADEMICO");
        var aluno = alunoService.retornaAluno(ra);

        if (aluno != null) return ResponseEntity.ok(aluno);
        return ResponseEntity.notFound().build();

    }

}

