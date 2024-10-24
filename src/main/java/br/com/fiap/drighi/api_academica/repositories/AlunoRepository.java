package br.com.fiap.drighi.api_academica.repositories;

import br.com.fiap.drighi.api_academica.domain.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Aluno findByRa(String ra);
    Aluno findByDocumentosNumero(String numeroCPF);

}
