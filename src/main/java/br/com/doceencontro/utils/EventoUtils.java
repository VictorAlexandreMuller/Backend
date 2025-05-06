package br.com.doceencontro.utils;

import br.com.doceencontro.exception.exceptions.JaParticipandoException;
import br.com.doceencontro.exception.exceptions.NotParticipandoException;
import br.com.doceencontro.model.Evento;

public class EventoUtils {

	public static boolean isParticipando(Evento evento, String usuarioId) {
		return evento.getParticipantes().stream()
        .anyMatch(p -> p.getId().equals(usuarioId));
	}
	
	public static void garantirParticipacao(Evento evento, String usuarioId) {
		boolean participacao = evento.getParticipantes().stream()
				.anyMatch(p -> p.getId().equals(usuarioId));
		
		if (!participacao) {
			throw new NotParticipandoException();
		}
	}
	
	public static void garantirNaoParticipacao(Evento evento, String usuarioId) {
		boolean participacao = evento.getParticipantes().stream()
				.anyMatch(p -> p.getId().equals(usuarioId));
		
		if (participacao) {
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
