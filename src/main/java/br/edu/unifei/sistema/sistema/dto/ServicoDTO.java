package br.edu.unifei.sistema.sistema.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.unifei.sistema.sistema.domain.Forum;
import br.edu.unifei.sistema.sistema.domain.Pagamento;
import br.edu.unifei.sistema.sistema.domain.Servico;

public class ServicoDTO {
private Long id;
    
    private String titulo;
    private String descricao;
    private Date dataCadastro;
    private int data;
    private double avaliacao;
    private List<Long> idTags = new ArrayList<Long>();
    private Forum forum;
    private Pagamento pagamento;
    
    public ServicoDTO() {}
    
    
    
	public ServicoDTO(String titulo, String descricao, Date dataCadastro, int data, double avaliacao,
			List<Long> idTags, Forum forum, Pagamento pagamento) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.dataCadastro = dataCadastro;
		this.data = data;
		this.avaliacao = avaliacao;
		this.idTags = idTags;
		this.forum = forum;
		this.pagamento = pagamento;
	}

	public ServicoDTO(Servico servico) {
		this.titulo = servico.getTitulo();
		this.descricao = servico.getDescricao();
		this.dataCadastro = servico.getDataCadastro();
		this.data = servico.getData();
		this.avaliacao = servico.getAvaliacao();
		this.forum = servico.getForum();
		this.pagamento = servico.getPagamento();
		servico.getTags().forEach(tag -> this.getIdTags().add(tag.getId()));
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
	
	public List<Long> getIdTags() {
		return idTags;
	}



	public void setIdTags(List<Long> idTags) {
		this.idTags = idTags;
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
