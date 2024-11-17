package br.edu.unifei.sistema.sistema.dto;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import br.edu.unifei.sistema.sistema.domain.Forum;
import br.edu.unifei.sistema.sistema.domain.Pagamento;
import br.edu.unifei.sistema.sistema.domain.Servico;
import br.edu.unifei.sistema.sistema.domain.Tag;
import br.edu.unifei.sistema.sistema.domain.User;

public class ServicoDTO {
private Long id;
    
    private String titulo;
    private String descricao;
    private Date dataCadastro;
    private int data;
    private double avaliacao;
    private User user;
    private List<Tag> tags;
    private Forum forum;
    private Pagamento pagamento;
    
    public ServicoDTO() {}
    
    
    
	public ServicoDTO(String titulo, String descricao, Date dataCadastro, int data, double avaliacao, User user,
			List<Tag> tags, Forum forum, Pagamento pagamento) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataCadastro = dataCadastro;
		this.data = data;
		this.avaliacao = avaliacao;
		this.user = user;
		this.tags = tags;
		this.forum = forum;
		this.pagamento = pagamento;
	}

	public ServicoDTO(Servico servico) {
		BeanUtils.copyProperties(servico, this);
	}

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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
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
    
    
}
