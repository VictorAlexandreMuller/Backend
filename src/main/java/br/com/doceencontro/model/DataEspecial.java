package br.com.doceencontro.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataEspecial {

//	@Id
//	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private LocalDateTime data;
	
//	@ManyToOne
//	@JoinColumn(name = "usuarios_id")
	private Usuario usuario;
}
