package br.edu.unifei.sistema.sistema.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.unifei.sistema.sistema.domain.Forum;
import br.edu.unifei.sistema.sistema.domain.Message;
import br.edu.unifei.sistema.sistema.domain.User;
import br.edu.unifei.sistema.sistema.dto.AddMessageRequest;
import br.edu.unifei.sistema.sistema.dto.MessageDTO;
import br.edu.unifei.sistema.sistema.repositories.ForumRepository;
import br.edu.unifei.sistema.sistema.repositories.MessageRepository;
import br.edu.unifei.sistema.sistema.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MessageService {
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired UserRepository userRepository;
	@Autowired ForumRepository forumRepository;
	
	@Transactional(readOnly = true)
	public List<MessageDTO> findAll(){
		List<Message> messages = messageRepository.findAll();
		List<MessageDTO> dto = messages.stream().map(MessageDTO::new).toList();
		return dto;
	}
	
	@Transactional
	public void addResposta (AddMessageRequest request, Long idMensagemRespondida) {
		Long idAutor = request.getIdAutor();
		User autor = userRepository.findById(idAutor)
				.orElseThrow(()-> new EntityNotFoundException("user not found with id: " + idAutor));
		Message resposta = request.getMenssagem();
		Message mensagemRespondida = messageRepository.findById(idMensagemRespondida)
				.orElseThrow(()-> new EntityNotFoundException("message not found with id: "+idMensagemRespondida));
		mensagemRespondida.getRespostas().add(resposta);
		resposta.setAutor(autor);
		resposta.setMensagemPai(mensagemRespondida);
		messageRepository.save(resposta);
	}
	
	@Transactional
	public MessageDTO getById(Long idMensagem){
		Message message = messageRepository.findById(idMensagem)
				.orElseThrow(()->new EntityNotFoundException("Message not found with id: "+ idMensagem));
		return new MessageDTO(message);
	}

	@Transactional
	public List<MessageDTO> getRespostas(Long idMensagem){
		Message menssagem = messageRepository.findById(idMensagem)
				.orElseThrow(()->new EntityNotFoundException("Message not found with id: "+ idMensagem));
		List<Message> respostas = menssagem.getRespostas();
		var dto = respostas.stream().map(MessageDTO::new).toList();
		return dto;
	}

	@Transactional
	public void deleteMensagem(Long idMensagem) {
	    Message mensagem = messageRepository.findById(idMensagem)
	            .orElseThrow(() -> new EntityNotFoundException("Mensagem nao encontrada com o ID: " + idMensagem));
	    List<Forum> forums = forumRepository.findAll();
	    
	    for(Forum f: forums) {
	    	if(!f.getMensagens().isEmpty()) {
	    		for(int i = 0; i<f.getMensagens().size(); i++) {
	    			if(f.getMensagens().get(i).getId() == idMensagem) {
	    				f.getMensagens().remove(i);
	    				forumRepository.save(f);
	    				messageRepository.delete(mensagem);
	    				return;
	    			}
	    				
	    			
	    		}
	    	
	    		
	    	}
	    }
	    
		messageRepository.delete(mensagem);
	}
}
