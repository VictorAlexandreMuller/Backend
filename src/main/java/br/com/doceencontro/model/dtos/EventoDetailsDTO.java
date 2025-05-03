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
    private List<RequisitoResponseDTO> presentes;
    private List<UsuarioResponseDTO> participantes = new ArrayList<>();
    private List<UsuarioResponseDTO> convidados = new ArrayList<>();

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
        this.presentes = evento.getRequisitos().stream()
                .map(RequisitoResponseDTO::new)
                .collect(Collectors.toList());
        this.organizador = new UsuarioResponseDTO(evento.getOrganizador());
        this.participantes = converterDtos(evento.getParticipantes());
        this.convidados = converterDtos(evento.getConvite().getDestinatarios());
    }

    private List<UsuarioResponseDTO> converterDtos(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioResponseDTO::new).collect(Collectors.toList());
    }

    private List<UsuarioResponseDTO> converterDtos(Set<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioResponseDTO::new).collect(Collectors.toList());
    }
}
