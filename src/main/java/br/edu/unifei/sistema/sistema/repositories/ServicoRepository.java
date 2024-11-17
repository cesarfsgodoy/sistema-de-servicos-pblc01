package br.edu.unifei.sistema.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unifei.sistema.sistema.domain.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
