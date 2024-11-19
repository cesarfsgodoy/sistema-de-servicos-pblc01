package br.edu.unifei.sistema.sistema.services;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.unifei.sistema.sistema.domain.Servico;
import br.edu.unifei.sistema.sistema.domain.Tag;
import br.edu.unifei.sistema.sistema.repositories.TagRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TagService {
	
	@Autowired
	private TagRepository tagRepository;
	
	@Transactional(readOnly = true)
	public List<Tag> findAll(){
		List<Tag> tags = tagRepository.findAll();
		tags.forEach(tag -> Hibernate.initialize(tag.getServicos()));
		return tags;
	}
	
	@Transactional(readOnly = true)
	public Tag findById(Long tagId) {
		Tag tag = tagRepository.findById(tagId)
				.orElseThrow(() -> new EntityNotFoundException("Tag not find with id: " + tagId));
		Hibernate.initialize(tag.getServicos());
		return tag;
	}
	
	@Transactional(readOnly = true)
	public List<Servico> getServicesByTag(Long tagId){
		Tag tag = findById(tagId);
		List<Servico> servicos = tag.getServicos();
		return servicos;
	}
	
	public void addTag(Tag tag) {
		tagRepository.save(tag);
	}
	
}
