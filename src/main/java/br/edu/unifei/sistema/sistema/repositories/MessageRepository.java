package br.edu.unifei.sistema.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unifei.sistema.sistema.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
