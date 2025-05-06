package br.com.doceencontro.utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.doceencontro.model.Evento;
import br.com.doceencontro.model.Notificacao;
import br.com.doceencontro.model.Requisito;
import br.com.doceencontro.model.Usuario;
import br.com.doceencontro.model.dtos.EventoDetailsDTO;
import br.com.doceencontro.model.dtos.EventoResponseDTO;
import br.com.doceencontro.model.dtos.NotificacaoResponseDTO;
import br.com.doceencontro.model.dtos.RequisitoResponseDTO;
import br.com.doceencontro.model.dtos.UsuarioDetailsDTO;
import br.com.doceencontro.model.dtos.UsuarioResponseDTO;

public class ConversorDTO {
	
	public static EventoResponseDTO evento(Evento evento) {
		return new EventoResponseDTO(evento);

	}

	public static List<EventoResponseDTO> eventos(List<Evento> eventos) {
		return eventos.stream().map(EventoResponseDTO::new).collect(Collectors.toList());
	}

	public static EventoDetailsDTO eventoDetails(Evento evento) {
		return new EventoDetailsDTO(evento);
	}

	public static UsuarioResponseDTO usuario(Usuario usuario) {
		return new UsuarioResponseDTO(usuario);
	}

	public static List<UsuarioResponseDTO> usuarios(List<Usuario> usuarios) {
		return usuarios.stream().map(UsuarioResponseDTO::new).collect(Collectors.toList());
	}

	public static List<UsuarioResponseDTO> usuariosSet(Set<Usuario> usuarios) {
		return usuarios.stream().map(UsuarioResponseDTO::new).collect(Collectors.toList());
	}
	
	public static UsuarioDetailsDTO usuarioDetails(Usuario usuario) {
		return new UsuarioDetailsDTO(usuario);
	}

	public static List<RequisitoResponseDTO> requisitos(List<Requisito> requisitos) {
		return requisitos.stream().map(RequisitoResponseDTO::new).collect(Collectors.toList());
	}
	
	public static List<NotificacaoResponseDTO> notificacoes(List<Notificacao> notificacoes) {
		return notificacoes.stream().map(NotificacaoResponseDTO::new).collect(Collectors.toList());
	}

}
