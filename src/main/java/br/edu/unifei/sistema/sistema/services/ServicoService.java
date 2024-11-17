package br.edu.unifei.sistema.sistema.services;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.unifei.sistema.sistema.domain.Servico;
import br.edu.unifei.sistema.sistema.dto.ServicoDTO;
import br.edu.unifei.sistema.sistema.repositories.ServicoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ServicoService {
	@Autowired
	private ServicoRepository servicoRepository;
	
	@Transactional(readOnly = true)
	public List<ServicoDTO> findAll(){
		List <Servico> result = servicoRepository.findAll();
		result.forEach(servico -> Hibernate.initialize(servico.getTags())); // Inicializa a coleção
//		List<ServicoDTO> dto = result.stream().map(ServicoDTO::new).toList();
//		return dto;
		return result.stream().map(ServicoDTO::new).toList();
	}
	
	@Transactional(readOnly = true)
	public ServicoDTO findById(Long id) {
		Servico result = servicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Serviço não encontrado com o ID: " + id));
		
		Hibernate.initialize(result.getTags());
		return new ServicoDTO(result);
	}
	
	@Transactional
	public void addServico(Servico servico) {
		servicoRepository.save(servico);
	}
}
