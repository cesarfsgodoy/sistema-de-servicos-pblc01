package br.edu.unifei.sistema.sistema.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String titulo;
    private String descricao;
    private Date dataCadastro;
    private int data;
    private double avaliacao;

    
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
    
    @ManyToMany
    @JoinTable(
        name = "servico_tag",
        joinColumns = @JoinColumn(name = "servico_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<Tag>();
    
    @OneToOne
    @JoinColumn(name = "forum_id")
    private Forum forum;
    
    @OneToOne
    @JoinColumn(name = "pagamento_id")
    private Pagamento pagamento;
    
    public Servico(Forum forum) {
    	this.forum = forum;
    }
    
    
    
    public Forum getForum() {
		return forum;
	}



	public void setForum(Forum forum) {
		this.forum = forum;
	}



	public Pagamento getPagamento() {
		return pagamento;
	}



	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}



	public Servico() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public double getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(double avaliacao) {
		this.avaliacao = avaliacao;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
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
		Servico other = (Servico) obj;
		return Objects.equals(id, other.id);
	}
    
    
}