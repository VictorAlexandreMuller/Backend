package br.com.doceencontro.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.doceencontro.model.Evento;
import br.com.doceencontro.model.dtos.EventoDetailsDTO;
import br.com.doceencontro.model.dtos.EventoRequestDTO;
import br.com.doceencontro.model.dtos.EventoResponseDTO;
import br.com.doceencontro.model.dtos.UsuarioResponseDTO;
import br.com.doceencontro.service.EventoService;
import br.com.doceencontro.utils.EventoUtils;
import br.com.doceencontro.utils.IdToken;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/eventos")
public class EventoController {

	private EventoService eventoService;

	public EventoController(EventoService eventoService) {
		this.eventoService = eventoService;
	}

	@GetMapping
	public List<EventoResponseDTO> obterTodos() {
		return eventoService.obterTodos();
	}

	@GetMapping("/{id}")
	public EventoDetailsDTO obterPorId(@PathVariable String id) {
		return eventoService.obterPorId(id);
	}

	@GetMapping("/ativos")
	public List<EventoResponseDTO> listarAtivos() {
		return eventoService.listarAtivos(IdToken.get());
	}
	
	@GetMapping("/passados")
	public List<EventoResponseDTO> listarPassados() {
		return eventoService.listarPassados(IdToken.get());
	}
	
	@GetMapping("/tipos")
	public List<String> tiposDeEvento() {
		return eventoService.tiposDeEvento();
	}
	
	@GetMapping("/participantes/{eventoId}")
	public List<UsuarioResponseDTO> listarParticipantes(@PathVariable String eventoId) {
		return eventoService.buscarParticipantes(eventoId);
	}

	@PostMapping
	public EventoResponseDTO registrarEvento(@RequestBody @Valid EventoRequestDTO eventoDTO) {
		return eventoService.registrarEvento(IdToken.get(), eventoDTO);
	}

	@PutMapping("/{eventoId}")
	public EventoResponseDTO editarEvento(@PathVariable String eventoId, @RequestBody @Valid EventoRequestDTO eventoDTO) {
		return eventoService.editarEvento(eventoId, eventoDTO, IdToken.get());
	}

	@DeleteMapping("/{eventoId}")
	public String excluirEvento(@PathVariable String eventoId) {
		return eventoService.excluirEvento(eventoId, IdToken.get());
	}

	@PostMapping("/participar/{eventoId}")
	public String participar(@PathVariable String eventoId) {
		return eventoService.participar(eventoId, IdToken.get());
	}

	@DeleteMapping("/participar/{eventoId}")
	public String removerParticipacao(@PathVariable String eventoId) {
		return eventoService.removerParticipacao(eventoId, IdToken.get());
	}

	@PutMapping("/{eventoId}/desativar")
	public String desativarEvento(@PathVariable String eventoId) {
		return eventoService.desativarEvento(eventoId, IdToken.get());
	}

	@GetMapping("/{eventoId}/verificar-autor")
	public boolean verificarAutor(@PathVariable String eventoId) {
		String usuarioId = IdToken.get();
		Evento evento = eventoService.findById(eventoId);
		return EventoUtils.verificarAutor(usuarioId, evento);
	}

}
