package br.edu.unifei.sistema.sistema.services;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.unifei.sistema.sistema.domain.Servico;
import br.edu.unifei.sistema.sistema.domain.Tag;
import br.edu.unifei.sistema.sistema.domain.User;
import br.edu.unifei.sistema.sistema.dto.ServicoDTO;
import br.edu.unifei.sistema.sistema.dto.TagDTO;
import br.edu.unifei.sistema.sistema.repositories.TagRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TagService {
	
	@Autowired
	private TagRepository tagRepository;
	
	@Transactional(readOnly = true)
	public List<TagDTO> findAll(){
		List<Tag> tags = tagRepository.findAllWithServices();
		//tags.forEach(tag -> Hibernate.initialize(tag.getServicos()));
		List<TagDTO> dto = tags.stream().map(TagDTO::new).toList();
		return dto;
	}
	
	@Transactional(readOnly = true)
	public TagDTO findById(Long tagId) {
		Tag tag = tagRepository.findByIdWithServices(tagId)
				.orElseThrow(() -> new EntityNotFoundException("Tag not find with id: " + tagId));
		//Hibernate.initialize(tag.getServicos());
		return new TagDTO(tag);
	}
	
	@Transactional(readOnly = true)
	public List<ServicoDTO> getServicesByTag(Long tagId){
		Tag tag = tagRepository.findById(tagId)
				.orElseThrow(() -> new EntityNotFoundException("Tag not found with id: "+tagId));
		List<Servico> servicos = tag.getServicos();
		servicos.forEach(servico -> Hibernate.initialize(servico.getForum().getMensagens()));
		List<ServicoDTO> dto = servicos.stream().map(ServicoDTO::new).toList();
		return dto;
	}
	
	@Transactional
	public void addTag(Tag tag) {
		tagRepository.save(tag);
	}

	@Transactional
	public void deleteTag(Long tagId) {
	    Tag tag = tagRepository.findById(tagId)
	            .orElseThrow(() -> new EntityNotFoundException("Tag nao encontrada com o ID: " + tagId));
	    tag.getServicos().forEach(servico -> servico.getTags().remove(tag));
	    tagRepository.delete(tag);
	}
	
}
