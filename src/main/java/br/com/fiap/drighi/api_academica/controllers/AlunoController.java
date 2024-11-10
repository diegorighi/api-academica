package br.com.fiap.drighi.api_academica.controllers;

import br.com.fiap.drighi.api_academica.domain.dto.InputAlunoDTO;
import br.com.fiap.drighi.api_academica.services.AlunoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<InputAlunoDTO> criarAluno(@RequestBody @Valid InputAlunoDTO alunoDTO) {
        log.info("Recebendo requisição para criar um aluno: {}", alunoDTO);
        if(alunoService.criarAluno(alunoDTO)) {
            return ResponseEntity.ok(alunoDTO);
        }
        return ResponseEntity.badRequest().build();
    }

}

