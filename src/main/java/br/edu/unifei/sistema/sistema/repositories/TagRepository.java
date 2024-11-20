package br.edu.unifei.sistema.sistema.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.unifei.sistema.sistema.domain.Servico;
import br.edu.unifei.sistema.sistema.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
	@Query("SELECT DISTINCT t FROM Tag t LEFT JOIN FETCH t.servicos")
    List<Tag> findAllWithServices();
	
	@Query("SELECT t FROM Tag t LEFT JOIN FETCH t.servicos WHERE t.id = :id")
    Optional<Tag> findByIdWithServices(@Param("id") Long id);
}
