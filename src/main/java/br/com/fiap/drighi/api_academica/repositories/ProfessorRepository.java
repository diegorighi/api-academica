package br.com.fiap.drighi.api_academica.repositories;

import br.com.fiap.drighi.api_academica.domain.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Professor findByDocumentosNumero(String documento);

}
