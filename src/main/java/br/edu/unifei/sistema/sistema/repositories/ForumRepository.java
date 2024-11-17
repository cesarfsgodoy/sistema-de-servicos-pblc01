package br.edu.unifei.sistema.sistema.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.unifei.sistema.sistema.domain.Forum;
import br.edu.unifei.sistema.sistema.domain.Servico;

public interface ForumRepository extends JpaRepository<Forum, Long>{
	
	@Query("SELECT DISTINCT f FROM Forum f LEFT JOIN FETCH f.mensagens")
    List<Forum> findAllWithMessages();
	
	@Query("SELECT f FROM Forum f LEFT JOIN FETCH f.mensagens WHERE f.id = :id")
    Optional<Forum> findByIdWithMessages(@Param("id") Long id);
}
