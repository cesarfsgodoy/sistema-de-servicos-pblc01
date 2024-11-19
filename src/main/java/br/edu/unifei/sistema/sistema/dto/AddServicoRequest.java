package br.edu.unifei.sistema.sistema.dto;

import br.edu.unifei.sistema.sistema.domain.Servico;

public class AddServicoRequest {
	private Long userId;
	private Servico servico;
	
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
