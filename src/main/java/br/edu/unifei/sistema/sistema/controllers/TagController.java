package br.edu.unifei.sistema.sistema.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unifei.sistema.sistema.domain.Tag;
import br.edu.unifei.sistema.sistema.services.TagService;

@RestController
@RequestMapping(value = "/tags")
public class TagController {
	@Autowired
	TagService tagService;
	
	@GetMapping
	public List<Tag> findAll() {
		return tagService.findAll();
	}
	
	@GetMapping(value = "/{tagId}")
	public Tag findById(@PathVariable Long tagId) {
		return tagService.findById(tagId);
	}
	
	@PostMapping
	public void addTag(@RequestBody Tag tag) {
		tagService.addTag(tag);
	}	
}
