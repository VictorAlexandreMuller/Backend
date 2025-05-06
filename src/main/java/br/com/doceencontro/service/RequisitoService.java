package br.com.doceencontro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.doceencontro.exception.exceptions.NotParticipandoException;
import br.com.doceencontro.exception.exceptions.RequisitoNotFoundException;
import br.com.doceencontro.model.Evento;
import br.com.doceencontro.model.Requisito;
import br.com.doceencontro.model.Usuario;
import br.com.doceencontro.model.dtos.RequisitoResponseDTO;
import br.com.doceencontro.repository.RequisitoRepository;
import br.com.doceencontro.utils.ConversorDTO;
import br.com.doceencontro.utils.EventoUtils;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RequisitoService {

	private RequisitoRepository repository;

	private EventoService eventoService;

	private UsuarioService usuarioService;

	public Requisito findById(String id) {
		Optional<Requisito> buscarRequisito = repository.findById(id);

		if (buscarRequisito.isEmpty()) {
			throw new RequisitoNotFoundException();
		}

		return buscarRequisito.get();
	}

	private RequisitoResponseDTO converterDTO(Requisito requisito) {
		return new RequisitoResponseDTO(requisito);
	}

	public Requisito criarRequisito(Requisito requisito, String eventoId) {
		Evento buscarEvento = eventoService.findById(eventoId);

		buscarEvento.addRequisito(requisito);

		return repository.save(requisito);
	}

	public Requisito editarRequisito(Requisito requisitoEditado, String requisitoId) {
		Requisito buscarRequisito = findById(requisitoId);

		buscarRequisito.setTitulo(requisitoEditado.getTitulo());
		buscarRequisito.setDescricao(requisitoEditado.getDescricao());

		return repository.save(buscarRequisito);
	}

	public String excluirRequisito(String requisitoId) {
		findById(requisitoId);

		repository.excluir(requisitoId);

		return "Requisito excluído com sucesso!";
	}

	public RequisitoResponseDTO addResponsavel(String requisitoId, String usuarioId) {
		Requisito buscarRequisito = findById(requisitoId);

		if (!EventoUtils.isParticipando(buscarRequisito.getEvento(), usuarioId)) {
			throw new NotParticipandoException();
		}

		Usuario buscarUsuario = usuarioService.findById(usuarioId);

		buscarRequisito.adicionarResponsavel(buscarUsuario);

		return converterDTO(repository.save(buscarRequisito));
	}

	public RequisitoResponseDTO removerResponsavel(String requisitoId, String usuarioId) {
		Requisito buscarRequisito = findById(requisitoId);

		if (!EventoUtils.isParticipando(buscarRequisito.getEvento(), usuarioId)) {
			throw new NotParticipandoException();
		}

		Usuario buscarUsuario = usuarioService.findById(usuarioId);

		buscarRequisito.removerResponsavel(buscarUsuario);

		return converterDTO(repository.save(buscarRequisito));
	}

	public List<RequisitoResponseDTO> buscarRequisitos(String eventoId) {
		return ConversorDTO.requisitos(eventoService.findById(eventoId).getRequisitos());
	}

}
