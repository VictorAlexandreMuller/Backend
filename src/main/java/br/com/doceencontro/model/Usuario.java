package br.com.doceencontro.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private String nome;

	private String email;

	private String senha;

	private LocalDateTime criadoEm;

	@OneToMany(mappedBy = "usuario")
	private List<Amizade> listaAmigos;

	@OneToMany(mappedBy = "organizador", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Evento> eventosCriados;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "usuario_evento", 
	joinColumns = @JoinColumn(name = "usuarios_id"),
	inverseJoinColumns = @JoinColumn(name = "eventos_id"))
	private List<Evento> eventosParticipados;

	@ManyToMany(mappedBy = "usuarios", cascade = CascadeType.ALL)
	private List<Notificacao> notificacoes;

	@ManyToMany(mappedBy = "responsaveis")
	private List<Requisito> requisitosEntregues;

	@ManyToMany(mappedBy = "usuarios", fetch = FetchType.LAZY)
	private List<Chat> chats;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Mensagem> mensagens;

	@ManyToMany(mappedBy = "destinatarios", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<Convite> convites;
	
	public Usuario editar(Usuario usuarioEditado) {
		this.nome =  usuarioEditado.nome;
		this.senha = usuarioEditado.senha;
		this.email = usuarioEditado.email;
		
		return this;
	}

	public Usuario(String nome, String email, String senha) {
		this.id = null;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.criadoEm = LocalDateTime.now();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}


	@Override
	public String getPassword() {
		return senha;
	}


	@Override
	public String getUsername() {
		return email;
	}
	
	@Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;

    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }




}


