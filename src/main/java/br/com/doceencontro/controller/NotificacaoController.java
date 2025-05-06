package br.com.doceencontro.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.doceencontro.model.Notificacao;
import br.com.doceencontro.model.dtos.NotificacaoResponseDTO;
import br.com.doceencontro.service.NotificacaoService;
import br.com.doceencontro.utils.IdToken;

@RestController
@RequestMapping("/usuarios/notificacoes")
public class NotificacaoController {

	private NotificacaoService notificacaoService;

	public NotificacaoController(NotificacaoService notificacaoService) {
		this.notificacaoService = notificacaoService;
	}
	
	@GetMapping
	public List<NotificacaoResponseDTO> obterNotificacoesUsuario() {
		return notificacaoService.obterNotificacoesUsuario(IdToken.get());
	}
}


