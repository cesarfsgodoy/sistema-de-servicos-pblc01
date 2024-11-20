package br.edu.unifei.sistema.sistema.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import br.edu.unifei.sistema.sistema.domain.Servico;
import br.edu.unifei.sistema.sistema.domain.User;

public class UserDTO {
	
	private Long id;
    
    private String name;
    private String email;
    private Double avaliacao;
    private List<Long> servicosId = new ArrayList<Long>();
    
    public UserDTO() {}
    
    public UserDTO(User user) {
    	this.id = user.getId();
    	this.name = user.getName();
    	this.email = user.getEmail();
    	this.avaliacao = user.getAvaliacao();
    	user.getServicos().forEach(servico -> this.servicosId.add(servico.getId()));
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Long> getServicosId() {
		return servicosId;
	}

	public void setServicosId(List<Long> servicosId) {
		this.servicosId = servicosId;
	}

	public Double getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}
    
    
}
