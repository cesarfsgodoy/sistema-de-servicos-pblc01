package br.edu.unifei.sistema.sistema.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.unifei.sistema.sistema.domain.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
	@Query("SELECT DISTINCT s FROM Servico s LEFT JOIN FETCH s.tags")
    List<Servico> findAllWithTags();
	
	@Query("SELECT s FROM Servico s LEFT JOIN FETCH s.tags WHERE s.id = :id")
    Optional<Servico> findByIdWithTags(@Param("id") Long id);
}
