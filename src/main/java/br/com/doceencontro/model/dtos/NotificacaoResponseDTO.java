package br.com.doceencontro.model.dtos;

import java.time.LocalDateTime;

import br.com.doceencontro.model.Notificacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacaoResponseDTO {
	private String id;
	
	private String titulo;
	
	private String corpo;
	
	private LocalDateTime data;

	public NotificacaoResponseDTO(Notificacao notificacao) {
		this.id = notificacao.getId();
		this.titulo = notificacao.getTitulo();
		this.corpo = notificacao.getCorpo();
		this.data = notificacao.getData();
	}
}
