package br.edu.unifei.sistema.sistema.domain;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import br.edu.unifei.sistema.sistema.dto.UserDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private String password;
    private Double avaliacao;
    
//    @ManyToMany
//    @JoinTable(
//        name = "usuario_servico",
//        joinColumns = @JoinColumn(name = "usuario_id"),
//        inverseJoinColumns = @JoinColumn(name = "servico_id")
//    )
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Servico> servicos;
    
    public User() {
    }


	public User(String name, String email, String password, Double avaliacao, List<Servico> servicos) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.avaliacao = avaliacao;
		this.servicos = servicos;
	}
	
	public User(UserDTO entity) {
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
    
    
}