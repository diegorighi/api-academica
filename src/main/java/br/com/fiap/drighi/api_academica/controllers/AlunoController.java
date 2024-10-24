package br.com.fiap.drighi.api_academica.controllers;

import br.com.fiap.drighi.api_academica.domain.dto.AlunoDTO;
import br.com.fiap.drighi.api_academica.services.AlunoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(@Autowired AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> criarAluno(@RequestBody AlunoDTO alunoDTO) {
        log.info("Iniciando a criação do aluno: {}", alunoDTO.getPrimeiroNome());
        log.info("Verificando se o aluno já existe na base de dados");
        Boolean existe = alunoService.alunoExiste(alunoDTO);
        if(existe) {
            log.error("Aluno já existe na base de dados");
            return ResponseEntity.badRequest().build();
        }
        log.info("Criando o registro academico (RA) do aluno");
        alunoService.criaRA(alunoDTO);
        log.info("Criando o aluno na base de dados");
        alunoService.criarAluno(alunoDTO);
        log.info("Aluno criado com sucesso: {}", alunoDTO.getPrimeiroNome());
        return ResponseEntity.ok(alunoDTO);
    }

}
