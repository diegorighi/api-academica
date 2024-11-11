package br.com.fiap.drighi.api_academica.repositories;

import br.com.fiap.drighi.api_academica.domain.entity.Disciplina;
import br.com.fiap.drighi.api_academica.domain.enums.DisciplinaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    Disciplina findByNome(DisciplinaEnum nome);

}
