package br.com.doceencontro.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.doceencontro.model.Evento;
import br.com.doceencontro.model.Notificacao;
import br.com.doceencontro.model.dtos.NotificacaoResponseDTO;
import br.com.doceencontro.repository.NotificacaoRepository;
import br.com.doceencontro.utils.ConversorDTO;

@Service
public class NotificacaoService {

	private EventoService eventoService;
	
	private NotificacaoRepository notificacaoRepository;

	public NotificacaoService(EventoService eventoService, NotificacaoRepository notificacaoRepository) {
		this.eventoService = eventoService;
		this.notificacaoRepository = notificacaoRepository;
	}
	
	@Async
	public void notificarParticipantes(Evento evento, String titulo, String corpo) {
		Notificacao notificacao = new Notificacao(null, titulo, corpo, LocalDateTime.now(), new ArrayList<>());
		notificacao.enviarNotificacao(evento.getParticipantes());
		
		notificacaoRepository.save(notificacao);
	}
	
	public List<NotificacaoResponseDTO> obterNotificacoesUsuario(String usuarioId) {
		return ConversorDTO.notificacoes(notificacaoRepository.findAllByUsuariosId(usuarioId));
	}
}



