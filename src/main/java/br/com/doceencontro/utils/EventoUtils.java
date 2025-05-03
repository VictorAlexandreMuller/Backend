package br.com.doceencontro.utils;

import java.util.List;
import java.util.Optional;

import br.com.doceencontro.exception.exceptions.JaParticipandoException;
import br.com.doceencontro.exception.exceptions.NotParticipandoException;
import br.com.doceencontro.model.Evento;
import br.com.doceencontro.model.Usuario;
import br.com.doceencontro.model.dtos.UsuarioResponseDTO;

public class EventoUtils {

	public static boolean isParticipando(Evento evento, String usuarioId) {
		Optional<Usuario> buscarUsuario = evento.getParticipantes().stream()
				.filter(p -> p.getId().equals(usuarioId))
				.findFirst();
		
		if (buscarUsuario.isPresent()) {
			return true;
		}
		return false;
	}
	
	public static void garantirParticipacao(Evento evento, String usuarioId) {
		Optional<Usuario> buscarUsuario = evento.getParticipantes().stream()
				.filter(p -> p.getId().equals(usuarioId))
				.findFirst();
		
		if (buscarUsuario.isEmpty()) {
			throw new NotParticipandoException();
		}
	}
	
	public static void garantirNaoParticipacao(Evento evento, String usuarioId) {
		Optional<Usuario> buscarUsuario = evento.getParticipantes().stream()
				.filter(p -> p.getId().equals(usuarioId))
				.findFirst();
		
		if (buscarUsuario.isPresent()) {
			throw new JaParticipandoException();
		}

	}
	
	public static boolean verificarAutor(String usuarioId, Evento evento) {		
		if (evento.getOrganizador().getId().equals(usuarioId)) {
			return true;
		}
		return false;
		
	}
	
}
