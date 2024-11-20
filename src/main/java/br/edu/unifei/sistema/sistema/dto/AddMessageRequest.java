package br.edu.unifei.sistema.sistema.dto;

import br.edu.unifei.sistema.sistema.domain.Message;

public class AddMessageRequest {
	private Long idAutor;
	private Message menssagem;
	public Long getIdAutor() {
		return idAutor;
	}
	public Message getMenssagem() {
		return menssagem;
	}
	
	
}
