package br.edu.unifei.sistema.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unifei.sistema.sistema.domain.Forum;

public interface ForumRepository extends JpaRepository<Forum, Long>{

}
