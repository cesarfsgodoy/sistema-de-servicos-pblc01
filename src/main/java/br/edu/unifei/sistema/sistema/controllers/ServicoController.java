package br.edu.unifei.sistema.sistema.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unifei.sistema.sistema.domain.Forum;
import br.edu.unifei.sistema.sistema.domain.Servico;
import br.edu.unifei.sistema.sistema.domain.User;
import br.edu.unifei.sistema.sistema.dto.AddServicoRequest;
import br.edu.unifei.sistema.sistema.dto.ServicoDTO;
import br.edu.unifei.sistema.sistema.repositories.ServicoRepository;
import br.edu.unifei.sistema.sistema.repositories.UserRepository;
import br.edu.unifei.sistema.sistema.services.ForumService;
import br.edu.unifei.sistema.sistema.services.ServicoService;
import br.edu.unifei.sistema.sistema.services.UserService;

@RestController
@RequestMapping(value = "/servicos")
public class ServicoController {
	@Autowired
	private ServicoService servicoService;
	
	@Autowired
	private ForumService forumService;
	
	@Autowired
	private UserService usuarioService;
	
	@GetMapping
	public List<ServicoDTO> findAll() {
		
		return servicoService.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public ServicoDTO findById(@PathVariable Long id) {
		return servicoService.findById(id);
	}
	
	@PostMapping
	public void addServico(@RequestBody AddServicoRequest request) {
		Forum forum = new Forum();
		
		Servico servico = request.getServico();
		Long userId = request.getUserId();
		
		User user = usuarioService.findById(userId);
		user.getServicos().add(servico);
		
		forumService.addForum(forum);
		servico.setForum(forum);
		servicoService.addServico(servico);
		usuarioService.addUser(user);
	}
	
	@PostMapping(value = "/{idServico}/tags/{idTag}")
	public ResponseEntity<Void> addTagToServico(
			@PathVariable Long idServico, 
			@PathVariable Long idTag) {
		
		servicoService.addTagToServico(idTag, idServico);
		
		return ResponseEntity.noContent().build();
	}
}
