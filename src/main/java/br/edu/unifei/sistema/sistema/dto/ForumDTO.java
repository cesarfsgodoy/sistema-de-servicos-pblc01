package br.edu.unifei.sistema.sistema.dto;

import java.util.List;

import org.springframework.beans.BeanUtils;

import br.edu.unifei.sistema.sistema.domain.Forum;
import br.edu.unifei.sistema.sistema.domain.Message;

public class ForumDTO {
	private Long id;
	private List<MessageDTO> mensagens;
	
	public ForumDTO() {}
	
	public ForumDTO(Forum forum) {
		this.id = forum.getId();
		this.mensagens = forum.getMensagens().stream().map(MessageDTO::new).toList();
	}

	public Long getId() {
		return id;
	}
	public List<MessageDTO> getMensagens() {
		return mensagens;
	}
	
	
}
