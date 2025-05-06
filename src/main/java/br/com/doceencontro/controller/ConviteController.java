package br.com.doceencontro.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.doceencontro.model.dtos.ConviteDTO;
import br.com.doceencontro.model.dtos.UsuarioResponseDTO;
import br.com.doceencontro.service.ConviteService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/eventos/convites")
@AllArgsConstructor
public class ConviteController {

	private ConviteService conviteService;

	@GetMapping("/{eventoId}")
	public List<UsuarioResponseDTO> buscarConvidados(@PathVariable String eventoId) {
		return conviteService.buscarConvidados(eventoId);
	}
	
	@PostMapping("/{eventoId}")
	public ConviteDTO convidarParticipantes(@PathVariable String eventoId, @RequestBody List<String> usuariosIds) {
		return conviteService.convidar(usuariosIds, eventoId);
	}
}



