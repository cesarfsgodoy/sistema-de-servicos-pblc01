package br.edu.unifei.sistema.sistema.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unifei.sistema.sistema.domain.Servico;
import br.edu.unifei.sistema.sistema.dto.ServicoDTO;
import br.edu.unifei.sistema.sistema.services.ServicoService;

@RestController
@RequestMapping(value = "/servicos")
public class ServicoController {
	@Autowired
	private ServicoService servicoService;
	
	@GetMapping
	public List<ServicoDTO > findAll() {
		
		return servicoService.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public ServicoDTO findById(@PathVariable Long id) {
		return servicoService.findById(id);
	}
	
	@PostMapping
	public void addServico(@RequestBody Servico servico) {
		servicoService.addServico(servico);
	}
}
