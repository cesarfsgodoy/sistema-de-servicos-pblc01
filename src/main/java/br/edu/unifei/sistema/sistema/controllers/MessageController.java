package br.edu.unifei.sistema.sistema.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unifei.sistema.sistema.dto.MessageDTO;
import br.edu.unifei.sistema.sistema.services.MessageService;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {
	@Autowired
	private MessageService messageService;;
	
	@GetMapping
	public List<MessageDTO> findAll() {
		return messageService.findAll();
	}
	
}
