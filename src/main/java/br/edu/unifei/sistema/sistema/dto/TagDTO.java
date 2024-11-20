package br.edu.unifei.sistema.sistema.dto;

import java.util.ArrayList;
import java.util.List;

import br.edu.unifei.sistema.sistema.domain.Tag;

public class TagDTO {
	private Long id;
	
	private String name;
	private List<Long> idServicos = new ArrayList<Long>();
	
	public TagDTO(Tag tag) {
		this.id = tag.getId();
		this.name = tag.getName();
		tag.getServicos().forEach(servico -> this.idServicos.add(servico.getId()));
	}
	
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public List<Long> getIdServicos() {
		return idServicos;
	}
	
}
