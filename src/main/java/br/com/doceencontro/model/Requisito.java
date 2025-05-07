package br.com.doceencontro.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "requisitos")
public class Requisito {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private String titulo;

	private String descricao;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "evento_id", referencedColumnName = "id")
	private Evento evento;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "requisito_usuario", joinColumns = @JoinColumn(name = "requisito_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private List<Usuario> responsaveis = new ArrayList<Usuario>();

	public void adicionarResponsavel(Usuario responsavel) {
		this.responsaveis.add(responsavel);
		responsavel.getRequisitosEntregues().add(this);

	}
	
		public void removerResponsavel(Usuario responsavel) {
			this.responsaveis.remove(responsavel);
			responsavel.getRequisitosEntregues().remove(this);
	
		}
}
