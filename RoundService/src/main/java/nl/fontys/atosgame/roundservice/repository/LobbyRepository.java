package nl.fontys.atosgame.roundservice.repository;

import nl.fontys.atosgame.roundservice.model.Lobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for lobby related operations
 * @author Eli
 */
@Repository
public interface LobbyRepository extends JpaRepository<Lobby, UUID> {
}
