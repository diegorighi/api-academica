package br.com.fiap.drighi.api_academica.controllers;

import br.com.fiap.drighi.api_academica.domain.dto.CursoDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @PostMapping
    public void cadastrar(@RequestBody CursoDTO cursoDTO) {

    }

}
