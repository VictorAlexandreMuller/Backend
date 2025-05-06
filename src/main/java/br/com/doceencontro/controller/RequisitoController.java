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

import br.com.doceencontro.model.Requisito;
import br.com.doceencontro.model.dtos.RequisitoResponseDTO;
import br.com.doceencontro.service.RequisitoService;
import br.com.doceencontro.utils.IdToken;

@RestController
@RequestMapping("/eventos/presentes")
public class RequisitoController {

	private RequisitoService service;

	public RequisitoController(RequisitoService service) {
		this.service = service;
	}
	
	@GetMapping("/{eventoId}")
	public List<RequisitoResponseDTO> buscarRequisitos(@PathVariable String eventoId) {
		return service.buscarRequisitos(eventoId);
	}
	
	@PostMapping("/{eventoId}")
	public Requisito criarRequisito(@RequestBody Requisito requisito, @PathVariable String eventoId) {
		return service.criarRequisito(requisito, eventoId);
	}
	
	@PutMapping("/{requisitoId}")
	public Requisito editarRequisito(@RequestBody Requisito requisito, @PathVariable String requisitoId) {
		return service.editarRequisito(requisito, requisitoId);
	}
	
	@DeleteMapping("/{requisitoId}")
	public String excluirRequisito(@PathVariable String requisitoId) {
		return service.excluirRequisito(requisitoId);
	}
	
	@PostMapping("/addResp/{requisitoId}")
	public RequisitoResponseDTO adicionarResponsavel(@PathVariable String requisitoId) {
		return service.addResponsavel(requisitoId, IdToken.get());
	}
	
	@DeleteMapping("/removerResp/{requisitoId}")
	public RequisitoResponseDTO removerResponsavel(@PathVariable String requisitoId) {
		return service.removerResponsavel(requisitoId, IdToken.get());
	}
	
}




