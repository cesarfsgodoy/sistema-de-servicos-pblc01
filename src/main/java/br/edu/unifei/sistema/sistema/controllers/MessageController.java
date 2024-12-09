package br.edu.unifei.sistema.sistema.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unifei.sistema.sistema.dto.AddMessageRequest;
import br.edu.unifei.sistema.sistema.dto.MessageDTO;
import br.edu.unifei.sistema.sistema.services.MessageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin
@RequestMapping(value = "/messages")
public class MessageController {
	@Autowired
	private MessageService messageService;;
	
	@GetMapping
	public List<MessageDTO> findAll() {
		return messageService.findAll();
	}
	
	@PostMapping(value = "/{idMenssagemRespondida}")
	public void addResposta(
			@RequestBody AddMessageRequest request, 
			@PathVariable Long idMenssagemRespondida) {
		messageService.addResposta(request, idMenssagemRespondida);
	}
	
	@GetMapping(value = "/{idMenssagem}")
	public MessageDTO getById(@PathVariable Long idMenssagem){
		return messageService.getById(idMenssagem);
	}

	@GetMapping(value = "/{idMenssagem}/respostas")
	public List<MessageDTO> getRespostas(@PathVariable Long idMenssagem){
		return messageService.getRespostas(idMenssagem);
	}
	
	@DeleteMapping(value = "/{idMensagem}")
    public ResponseEntity<Void> deleteMensagem(@PathVariable Long idMensagem) {
        messageService.deleteMensagem(idMensagem);
        return ResponseEntity.noContent().build();
    }
}
