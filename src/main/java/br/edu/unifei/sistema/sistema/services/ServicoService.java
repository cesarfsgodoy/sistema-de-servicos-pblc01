package br.edu.unifei.sistema.sistema.services;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.unifei.sistema.sistema.domain.Forum;
import br.edu.unifei.sistema.sistema.domain.Message;
import br.edu.unifei.sistema.sistema.domain.Servico;
import br.edu.unifei.sistema.sistema.domain.Tag;
import br.edu.unifei.sistema.sistema.domain.User;
import br.edu.unifei.sistema.sistema.dto.AddMessageRequest;
import br.edu.unifei.sistema.sistema.dto.AddServicoRequest;
import br.edu.unifei.sistema.sistema.dto.MessageDTO;
import br.edu.unifei.sistema.sistema.dto.ServicoDTO;
import br.edu.unifei.sistema.sistema.repositories.ForumRepository;
import br.edu.unifei.sistema.sistema.repositories.MessageRepository;
import br.edu.unifei.sistema.sistema.repositories.ServicoRepository;
import br.edu.unifei.sistema.sistema.repositories.TagRepository;
import br.edu.unifei.sistema.sistema.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ServicoService {
	@Autowired
	private ServicoRepository servicoRepository;
	
	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ForumRepository forumRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional(readOnly = true)
	public List<ServicoDTO> findAll(){
		List <Servico> result = servicoRepository.findAllWithTags();
		result.forEach(servico -> Hibernate.initialize(servico.getForum().getMensagens()));
		List<ServicoDTO> dto = result.stream().map(ServicoDTO::new).toList();
		return dto;
	}
	
	@Transactional(readOnly = true)
	public ServicoDTO findById(Long id) {
		Servico result = servicoRepository.findByIdWithTags(id).orElseThrow(() -> new EntityNotFoundException("Serviço	 não encontrado com o ID: " + id));
		Hibernate.initialize(result.getForum().getMensagens());
		//Hibernate.initialize(result.getUser().getServicos());
		//System.out.println(result.getId());
		return new ServicoDTO(result);
		//Hibernate.initialize(result.getUser().getServicos());
		
		//return result;
	}
	
	@Transactional
	public void addServico(AddServicoRequest request) {
		Servico servico = request.getServico();
		User usuario = userRepository.findById(request.getUserId())
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + request.getUserId()));
		servico.setForum(new Forum());
		usuario.getServicos().add(servico);
		servicoRepository.save(servico);
	}
	
	@Transactional
	public void addTagToServico(Long idTag, Long idServico) {
		Servico servico = servicoRepository.findById(idServico)
				.orElseThrow(() -> new EntityNotFoundException("Serviço	 não encontrado com o ID: " + idServico));
		Tag tag = tagRepository.findById(idTag)
				.orElseThrow(() -> new EntityNotFoundException("Tag not find with id: " + idTag));
		servico.getTags().add(tag);
		tag.getServicos().add(servico);
		tagRepository.save(tag);
		servicoRepository.save(servico);
	}
	
	@Transactional(readOnly = true)
	public List<MessageDTO> getMessagesByService(Long idServico) {
		Servico servico = servicoRepository.findById(idServico)
				.orElseThrow(()->new EntityNotFoundException("service not found with id: "+idServico));
		List<Message> messages = servico.getForum().getMensagens();
		List<MessageDTO> dto = messages.stream().map(MessageDTO::new).toList();
		return dto;
	}
	
	@Transactional
	public void addMensagem(Long idServico, AddMessageRequest request) {
		Servico servico = servicoRepository.findById(idServico)
				.orElseThrow(()->new EntityNotFoundException("service not found with id: "+idServico));
		Forum forum = servico.getForum();
		Long idAutor = request.getIdAutor();
		User autor = userRepository.findById(idAutor)
				.orElseThrow(()-> new EntityNotFoundException("user not found with id: "+idAutor));
		Message menssagem = request.getMenssagem();
		menssagem.setAutor(autor);
		forum.getMensagens().add(menssagem);
		
		//mensagem.setForum(forum);

		messageRepository.save(menssagem);
		servicoRepository.save(servico);
		forumRepository.save(forum);
		
	}
	
	@Transactional
	public ServicoDTO updateServico(Long idServico, Servico servicoUp){
		try{
			Servico s = servicoRepository.getReferenceById(idServico);
			s.setDescricao(servicoUp.getDescricao());
			s.setTitulo(servicoUp.getTitulo());
			s = servicoRepository.save(s);
			return new ServicoDTO(s);
		} catch (EntityNotFoundException e){
			throw new EntityNotFoundException("Servico not found with id: " + idServico);
		}
	}
}
