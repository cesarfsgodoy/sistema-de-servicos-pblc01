package br.edu.unifei.sistema.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unifei.sistema.sistema.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
