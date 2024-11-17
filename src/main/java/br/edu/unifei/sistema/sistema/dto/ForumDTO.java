package br.edu.unifei.sistema.sistema.dto;

import java.util.List;

import org.springframework.beans.BeanUtils;

import br.edu.unifei.sistema.sistema.domain.Forum;
import br.edu.unifei.sistema.sistema.domain.Message;

public class ForumDTO {
	private Long id;
	private List<Message> mensagens;
	
	public ForumDTO() {}
	
	public ForumDTO(Forum forum) {
		BeanUtils.copyProperties(forum, this);
	}
	
	public ForumDTO(List<Message> mensagens) {
		super();
		this.mensagens = mensagens;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Message> getMensagens() {
		return mensagens;
	}
	public void setMensagens(List<Message> mensagens) {
		this.mensagens = mensagens;
	}
	
	
}
