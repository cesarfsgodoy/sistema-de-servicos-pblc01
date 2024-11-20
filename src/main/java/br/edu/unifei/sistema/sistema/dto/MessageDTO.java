package br.edu.unifei.sistema.sistema.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.unifei.sistema.sistema.domain.Message;

public class MessageDTO {
	private Long id;    
    private String conteudo;
    private Date data;
    private Long idAutor;
   // private Long idMensagemPai;
    private List<Long> idRespostas = new ArrayList<Long>();
    //private Long idForum;
    
    public MessageDTO(Message message) {
    	this.id = message.getId();
    	this.conteudo = message.getConteudo();
    	this.data = message.getData();
    	this.idAutor = message.getAutor().getId();
    	//this.idMensagemPai = message.getMensagemPai().getId();
    	//this.idForum = message.getForum().getId();
    	message.getRespostas().forEach(resposta -> this.getIdRespostas().add(resposta.getId()));
    }
    
    
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
	public Long getIdAutor() {
		return idAutor;
	}
	public void setIdAutor(Long idAutor) {
		this.idAutor = idAutor;
	}
	public List<Long> getIdRespostas() {
		return idRespostas;
	}
	public void setIdRespostas(List<Long> idRespostas) {
		this.idRespostas = idRespostas;
	}
//	public Long getIdForum() {
//		return idForum;
//	}
//	public void setIdForum(Long idForum) {
//		this.idForum = idForum;
//	}
    
    
    
}
