package br.com.doceencontro.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.doceencontro.model.Evento;
import br.com.doceencontro.model.Usuario;
import jakarta.transaction.Transactional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, String> {

	// @Modifying
	// @Transactional
	// @Query("DELETE FROM Evento e WHERE e.id = :id")
	// void excluir(String id);

	@Modifying
	@Transactional
	@Query("UPDATE Evento e SET e.ativo = false WHERE e.id = :id")
	void desativar(String id);

	@Query("SELECT p from Evento e JOIN e.participantes p WHERE p.id = :id AND e.id = :eventoId")
	Optional<Usuario> verificarParticipacao(String id, String eventoId);

	@Query("SELECT u FROM Usuario u JOIN u.eventosCriados e WHERE u.id = :usuarioId AND e.id = :eventoId")
	Optional<Usuario> findAutor(String usuarioId, String eventoId);

	// @Query("SELECT e FROM Usuario u JOIN u.eventosParticipados e "
	// + "WHERE u.id = :usuarioId AND e.data >= CURRENT_TIMESTAMP")
	// List<Evento> listarEventosAtivos(String usuarioId);

	@Query("SELECT e FROM Usuario u JOIN u.eventosParticipados e "
			+ "WHERE u.id = :usuarioId AND e.data >= :data")
	List<Evento> listarEventosAtivos(String usuarioId, LocalDateTime data);

	@Query("SELECT e FROM Usuario u JOIN u.eventosParticipados e "
			+ "WHERE u.id = :usuarioId AND e.data <= :data")
	List<Evento> listarEventosPassados(String usuarioId, LocalDateTime data);
}
