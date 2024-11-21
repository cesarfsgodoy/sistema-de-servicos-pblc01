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
//	    Hibernate.initialize(result.getServicos());
//	    result.getServicos().forEach(servico -> Hibernate.initialize(servico.getTags()));
//	    result.getServicos().forEach(servico -> Hibernate.initialize(servico.getForum().getMensagens()));

	    return new UserDTO(result);

	}
	
	@Transactional(readOnly = true)
	public User findByIdUser(Long userId) {
	    User result = userRepository.findById(userId)
	        .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + userId));
	    Hibernate.initialize(result.getServicos());
//	    result.getServicos().forEach(servico -> Hibernate.initialize(servico.getTags()));
//	    result.getServicos().forEach(servico -> Hibernate.initialize(servico.getForum().getMensagens()));

	    return result;

	}

//	@Transactional(readOnly = true)
//	public List<User> findAll() {
//	    List<User> result = userRepository.findAll();
//	    result.forEach(user -> Hibernate.initialize(user.getServicos())); // Inicializa a coleção
//	    result.forEach(user -> user.getServicos().forEach(servico -> Hibernate.initialize(servico.getForum().getMensagens())));
//	    result.forEach(user -> user.getServicos().forEach(servico -> Hibernate.initialize(servico.getTags())));
//	    return result;
//	}
	
	@Transactional(readOnly = true)
	public List<UserDTO> findAll(){
		
		List<User> result = userRepository.findAll();
		List<UserDTO> dto = result.stream().map(x -> new UserDTO(x)).toList();
		
		
		return dto;
	}
	
	@Transactional
	public void deleteUser(Long userId) {
	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + userId));
	    userRepository.delete(user);
	}

	
	@Transactional
	public UserDTO updateUser(Long userId, User userUpdate) {
	    try {
	        User user = userRepository.getReferenceById(userId);

	        user.setName(userUpdate.getName());
	        user.setEmail(userUpdate.getEmail());
	        user.setPassword(userUpdate.getPassword());
	        user.setAvaliacao(userUpdate.getAvaliacao());
	        user = userRepository.save(user);
	        return new UserDTO(user);
	    } catch (EntityNotFoundException e) {
	        throw new EntityNotFoundException("Usuário não encontrado com o ID: " + userId);
	    }
	}


	
}
