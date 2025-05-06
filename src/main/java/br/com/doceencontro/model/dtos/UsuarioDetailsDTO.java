package br.com.doceencontro.model.dtos;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.doceencontro.model.Usuario;
import lombok.Data;

@Data
public class UsuarioDetailsDTO {

	private String id;

	private String nome;
	
	private String email;
	
	private String criadoEm;
	
    @JsonIgnore
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH:mm");
	
	public UsuarioDetailsDTO(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.criadoEm = usuario.getCriadoEm().format(dtf);
	}
}
