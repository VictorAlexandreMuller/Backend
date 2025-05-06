package br.com.doceencontro.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.Where;

import br.com.doceencontro.model.dtos.EventoRequestDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "ativo = true")
@Entity
@Table(name = "eventos")
public class Evento {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private String titulo;

	private String descricao;

	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	private LocalDateTime data;

	@Column(nullable = false) // <-- Aqui adicionamos o ativo no mapeamento
	private Boolean ativo = true;

	@OneToOne(mappedBy = "evento", cascade = CascadeType.ALL)
	private Endereco endereco;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "usuario_id", referencedColumnName = "id")
	private Usuario organizador;

	@ManyToMany(mappedBy = "eventosParticipados")
	private List<Usuario> participantes;

	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
	private List<Arquivo> arquivos;

	@OneToMany(mappedBy = "evento")
	private List<Requisito> requisitos;

	@OneToOne(mappedBy = "evento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Chat chat;

	@OneToOne(mappedBy = "evento", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Convite convite;

	public Evento editar(EventoRequestDTO eventoDTO) {
		setTitulo(eventoDTO.titulo());
		setDescricao(eventoDTO.descricao());
		setTipo(Tipo.fromString(eventoDTO.tipo()));
		setData(eventoDTO.data());
		endereco.setLocal(eventoDTO.local());
		endereco.setCidade(eventoDTO.cidade());
		endereco.setEstado(eventoDTO.estado());
		endereco.setRua(eventoDTO.rua());
		endereco.setNumero(eventoDTO.numero());

		return this;
	}

	public void addParticipante(Usuario participante) {
		this.participantes.add(participante);
		participante.getEventosParticipados().add(this);
	}

	public void removerParticipante(Usuario participante) {
		this.participantes.remove(participante);
		participante.getEventosParticipados().remove(this);
	}

	public void addRequisito(Requisito requisito) {
		this.requisitos.add(requisito);
		requisito.setEvento(this);
	}
}
