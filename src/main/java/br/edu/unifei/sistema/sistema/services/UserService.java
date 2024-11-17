package br.edu.unifei.sistema.sistema.services;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.unifei.sistema.sistema.domain.User;
import br.edu.unifei.sistema.sistema.dto.UserDTO;
import br.edu.unifei.sistema.sistema.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public void addUser(User user) {
		userRepository.save(user);
	}
	
//	@Transactional(readOnly = true)
//	public UserDTO findById(Long userId) {
//		User result = userRepository.findById(userId).get();
//		UserDTO dto = new UserDTO(result);	
//		return dto;
//	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long userId) {
	    User result = userRepository.findById(userId)
	        .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + userId));
	    Hibernate.initialize(result.getServicos());
	    return new UserDTO(result);
	}


	
//	@Transactional(readOnly = true)
//	public List<UserDTO> findAll(){
//		
//		List<User> result = userRepository.findAll();
//		List<UserDTO> dto = result.stream().map(x -> new UserDTO(x)).toList();
//		
//		
//		return dto;
//	}
	
	@Transactional(readOnly = true)
	public List<UserDTO> findAll() {
	    List<User> result = userRepository.findAll();
	    result.forEach(user -> Hibernate.initialize(user.getServicos())); // Inicializa a coleção
	    return result.stream().map(UserDTO::new).toList();
	}
}