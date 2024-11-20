package br.edu.unifei.sistema.sistema.services;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.unifei.sistema.sistema.domain.Forum;
import br.edu.unifei.sistema.sistema.dto.ForumDTO;
import br.edu.unifei.sistema.sistema.repositories.ForumRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ForumService {
	@Autowired
	private ForumRepository forumRepository;
	
	@Transactional
	public void addForum(Forum forum) {
		forumRepository.save(forum);
	}
	
	@Transactional(readOnly = true)
	public List<ForumDTO>findAll() {
		List<Forum>result = forumRepository.findAllWithMessages();
		return result.stream().map(ForumDTO::new).toList();
	}
	
	@Transactional(readOnly = true)
	public ForumDTO findById(Long id) {
		Forum result = forumRepository.findByIdWithMessages(id).orElseThrow(() -> new EntityNotFoundException("Forum não encontrado com o ID: " + id));
		Hibernate.initialize(result.getMensagens());
		
		ForumDTO dto = new ForumDTO(result);
	
		return dto;
	}
}
