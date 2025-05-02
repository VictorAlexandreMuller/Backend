package br.com.doceencontro.model.dtos;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import br.com.doceencontro.utils.MensagemErro;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EventoRequestDTO(
		@NotBlank(message = MensagemErro.OBRIGATORIO) @Length(min = 5, message = MensagemErro.MIN) 
		@Length(max = 100, message = MensagemErro.MAX)
		String titulo, 
		
		String descricao, 
		
		@NotBlank(message = MensagemErro.OBRIGATORIO)
		String tipo, 
		
		@NotNull(message = MensagemErro.OBRIGATORIO)
		// @Future(message = MensagemErro.FUTURO)
		LocalDateTime data, 
		
		String local, 
		
		@NotBlank(message = MensagemErro.OBRIGATORIO)
		String estado, 
		
		@NotBlank(message = MensagemErro.OBRIGATORIO)
		String cidade, 
		
		@NotBlank(message = MensagemErro.OBRIGATORIO)
		String rua, 
		
		Integer numero
		) {

}
