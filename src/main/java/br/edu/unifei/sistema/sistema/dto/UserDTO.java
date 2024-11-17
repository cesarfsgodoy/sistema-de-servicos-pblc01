package br.edu.unifei.sistema.sistema.dto;

import java.util.List;

import org.springframework.beans.BeanUtils;

import br.edu.unifei.sistema.sistema.domain.Servico;
import br.edu.unifei.sistema.sistema.domain.User;

public class UserDTO {
	
	private Long id;
    
    private String name;
    private String email;
    private String password;
    private Double avaliacao;
    private List<Servico> servicos;
    
    public UserDTO() {}
    
    public UserDTO(User entity) {
    	BeanUtils.copyProperties(entity, this);
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Double getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(Double avaliacao) {
		this.avaliacao = avaliacao;
	}
	public List<Servico> getServicos() {
		return servicos;
	}
	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
    
    
}
