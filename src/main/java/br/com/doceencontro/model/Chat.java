package br.com.doceencontro.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "chats")
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String nome;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "evento_id", referencedColumnName = "id")
	private Evento evento;
	
	@ManyToMany
	@JoinTable(name = "chat_usuario",
	joinColumns = @JoinColumn(name = "chat_id"),
	inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private List<Usuario> usuarios;
	
	@OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
	private List<Mensagem> mensagens;

	public Chat(Evento evento) {
		this.id = null;
		this.nome = "Chat de " + evento.getTitulo();
		this.evento = evento;
		this.usuarios = new ArrayList<Usuario>();
		this.mensagens = new ArrayList<Mensagem>();
	}
	
	public void adicionarParticipante(Usuario participante) {
		this.usuarios.add(participante);
		participante.getChats().add(this);
	}
	
	public void removerParticipante(Usuario participante) {
		this.usuarios.remove(participante);
		participante.getChats().remove(this);
	}
}




