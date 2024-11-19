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
	public List<Servico> findAll(){
		List <Servico> result = servicoRepository.findAllWithTags();
		result.forEach(servico -> Hibernate.initialize(servico.getForum().getMensagens()));
		//List<ServicoDTO> dto = result.stream().map(ServicoDTO::new).toList();
		return result;
	}
	
	@Transactional(readOnly = true)
	public Servico findById(Long id) {
		Servico result = servicoRepository.findByIdWithTags(id).orElseThrow(() -> new EntityNotFoundException("Serviço	 não encontrado com o ID: " + id));
		Hibernate.initialize(result.getForum().getMensagens());
		//Hibernate.initialize(result.getUser().getServicos());
		//System.out.println(result.getId());
		//return new ServicoDTO(result);
		//Hibernate.initialize(result.getUser().getServicos());
		return result;
	}
	
	@Transactional
	public void addServico(Servico servico) {
		servicoRepository.save(servico);
	}
}
