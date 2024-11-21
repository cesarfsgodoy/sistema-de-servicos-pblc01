package br.edu.unifei.sistema.sistema.services;

import java.util.List;

import javax.annotation.processing.Messager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.unifei.sistema.sistema.domain.Message;
import br.edu.unifei.sistema.sistema.domain.Servico;
import br.edu.unifei.sistema.sistema.domain.User;
import br.edu.unifei.sistema.sistema.dto.AddMessageRequest;
import br.edu.unifei.sistema.sistema.dto.MessageDTO;
import br.edu.unifei.sistema.sistema.repositories.MessageRepository;
import br.edu.unifei.sistema.sistema.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MessageService {
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired UserRepository userRepository;
	
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
		messageRepository.delete(mensagem);
	}
}
