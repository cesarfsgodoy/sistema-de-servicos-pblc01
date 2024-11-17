package br.edu.unifei.sistema.sistema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unifei.sistema.sistema.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
   
}
