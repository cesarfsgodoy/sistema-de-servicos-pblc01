package br.edu.unifei.sistema.sistema.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.edu.unifei.sistema.sistema.dto.ForumDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Forum {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> mensagens = new ArrayList<Message>();
    
    public Forum() {}
    
//    public Forum(ForumDTO forumDTO) {
//    	this.id = forumDTO.getId();
//    	this.mensagens = forumDTO.getMensagens();
//    }
//    
    

	public Forum(List<Message> mensagens) {
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
		Forum other = (Forum) obj;
		return Objects.equals(id, other.id);
	}
    
    
}
