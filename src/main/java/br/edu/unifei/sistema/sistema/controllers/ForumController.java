package br.edu.unifei.sistema.sistema.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unifei.sistema.sistema.domain.Forum;
import br.edu.unifei.sistema.sistema.dto.ForumDTO;
import br.edu.unifei.sistema.sistema.services.ForumService;

@RestController
@RequestMapping(value = "/foruns")
public class ForumController {
	@Autowired
	private ForumService forumService;
	
	@GetMapping
	public List<ForumDTO>findAll(){
		return forumService.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public ForumDTO findById(@PathVariable Long id) {
		return forumService.findById(id);
	}
	
	@PostMapping
	public void addForum(@RequestBody Forum forum){
		forumService.addForum(forum);
	}
}
