package br.com.doceencontro.model.dtos;

import java.util.List;
import java.util.stream.Collectors;

import br.com.doceencontro.model.Requisito;
import br.com.doceencontro.model.Usuario;
import br.com.doceencontro.utils.IdToken;
import lombok.Data;

@Data
public class RequisitoResponseDTO {

	private String id;
	
	private String titulo;
	
	private String descricao;
	
	private List<UsuarioResponseDTO> responsaveis;
	
	private boolean isResponsavel = false;
	
	public RequisitoResponseDTO(Requisito requisito) {
        this.id = requisito.getId();
        this.titulo = requisito.getTitulo();
        this.descricao = requisito.getDescricao();
        this.responsaveis = converterUsuarioDTOs(requisito.getResponsaveis());
        
        String usuarioId = IdToken.get();
        
        this.isResponsavel = requisito.getResponsaveis().stream()
            .anyMatch(responsavel -> responsavel.getId().equals(usuarioId));
    }
	
	private List<UsuarioResponseDTO> converterUsuarioDTOs(List<Usuario> responsaveis) {
		return responsaveis.stream()
				.map(r -> new UsuarioResponseDTO(r))
				.collect(Collectors.toList());
	}
}



