package br.edu.unifei.sistema.sistema.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Message {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String conteudo;
    private Date data;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User autor;

//    @ManyToOne
//    @JoinColumn(name = "forum_id")
//    private Forum forum;
    
//    @ManyToOne
//    @JoinColumn(name = "mensagem_pai_id") // Nome da FK no banco
//    private Message mensagemPai;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> respostas = new ArrayList<>();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public User getAutor() {
		return autor;
	}

	public void setAutor(User autor) {
		this.autor = autor;
	}

//	public Forum getForum() {
//		return forum;
//	}
//
//	public void setForum(Forum forum) {
//		this.forum = forum;
//	}
	
	

//	public Message getMensagemPai() {
//		return mensagemPai;
//	}
//
//	public void setMensagemPai(Message mensagemPai) {
//		this.mensagemPai = mensagemPai;
//	}

	public List<Message> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Message> respostas) {
		this.respostas = respostas;
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
		Message other = (Message) obj;
		return Objects.equals(id, other.id);
	}
    
    
}
