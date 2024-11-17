package br.edu.unifei.sistema.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unifei.sistema.sistema.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
