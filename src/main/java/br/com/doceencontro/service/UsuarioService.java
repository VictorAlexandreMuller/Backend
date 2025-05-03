package br.com.doceencontro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.doceencontro.exception.exceptions.EmailEmUsoException;
import br.com.doceencontro.exception.exceptions.UsuarioNotFoundException;
import br.com.doceencontro.model.Evento;
import br.com.doceencontro.model.Usuario;
import br.com.doceencontro.model.dtos.UsuarioDetailsDTO;
import br.com.doceencontro.model.dtos.UsuarioResponseDTO;
import br.com.doceencontro.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public Usuario findById(String id) {
		Optional<Usuario> buscarUsuario = usuarioRepository.findById(id);

		if (buscarUsuario.isEmpty()) {
			throw new UsuarioNotFoundException();
		}

		return buscarUsuario.get();
	}
	
	public Usuario buscarPorEmailExcetoId(String email, String id) {
		Optional<Usuario> buscarUsuario = usuarioRepository.findByEmailAndIdNot(email, id);

		if (buscarUsuario.isEmpty()) {
			throw new UsuarioNotFoundException();
		}

		return buscarUsuario.get();
	}

	private List<UsuarioResponseDTO> converterDtos(List<Usuario> usuario) {
		return usuario.stream().map(u -> new UsuarioResponseDTO(u)).collect(Collectors.toList());
	}

	private UsuarioDetailsDTO converterDto(Usuario usuario) {
		return new UsuarioDetailsDTO(usuario);
	}
	
	private boolean verificarEmailExistente(String email, String id) {
		return usuarioRepository.findByEmailAndIdNot(email, id).isPresent();
	}

	public List<UsuarioResponseDTO> obterTodos() {
		return converterDtos(usuarioRepository.findAll());
	}

	public UsuarioDetailsDTO obterPorId(String id) {
		Optional<Usuario> buscarUsuario = usuarioRepository.findById(id);

		if (buscarUsuario.isEmpty()) {
			throw new UsuarioNotFoundException();
		}

		return converterDto(buscarUsuario.get());
	}

	public UsuarioDetailsDTO registrarUsuário(Usuario usuario) {
		Usuario usuariodb = usuarioRepository.save(usuario);

		return converterDto(usuariodb);
	}

	public UsuarioDetailsDTO editarUsuario(String id, Usuario usuarioEditado) {
		if (verificarEmailExistente(usuarioEditado.getEmail(), id)) {
			throw new EmailEmUsoException();
		}
		Usuario buscarUsuario = findById(id);

		return converterDto(usuarioRepository.save(buscarUsuario.editar(usuarioEditado)));
	}

	@Transactional
	public String excluirUsuario(String id) {
		Usuario buscarUsuario = findById(id);

		usuarioRepository.excluir(buscarUsuario.getId());

		return "Usuário excluído com sucesso.";
	}

	public ResponseEntity<Void> verificarParticipacao(String eventoId, String usuarioId) {
		Usuario usuario = findById(usuarioId);
		
		Optional<Evento> buscarEvento = usuario.getEventosParticipados().stream()
				.filter(e -> e.getId().equals(eventoId))
				.findFirst();
		
		if (buscarEvento.isPresent()) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
