package br.com.doceencontro.model.dtos;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.doceencontro.model.Endereco;
import br.com.doceencontro.model.Evento;
import br.com.doceencontro.model.Usuario;
import br.com.doceencontro.utils.ConversorDTO;
import br.com.doceencontro.utils.IdToken;
import lombok.Data;

@Data
public class EventoDetailsDTO {

    private String id;
    private String titulo;
    private String descricao;
    private String tipo;
    private String data;
    private Boolean ativo;
    private Endereco endereco;
    
    private UsuarioResponseDTO organizador;
    
    private boolean isAutor;

    @JsonIgnore
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public EventoDetailsDTO(Evento evento) {
        this.id = evento.getId();
        this.titulo = evento.getTitulo();
        this.descricao = evento.getDescricao();
        this.tipo = evento.getTipo().toString();
        this.data = evento.getData().format(dtf);
        this.ativo = evento.getAtivo();
        this.endereco = evento.getEndereco();

        this.organizador = new UsuarioResponseDTO(evento.getOrganizador());

        this.isAutor = IdToken.get().equals(evento.getOrganizador().getId());
    }
}
