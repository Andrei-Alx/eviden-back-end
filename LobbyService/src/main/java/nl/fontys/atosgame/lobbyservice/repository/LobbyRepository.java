package nl.fontys.atosgame.lobbyservice.repository;

import java.util.UUID;
import nl.fontys.atosgame.lobbyservice.model.Lobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for lobbies.
 */
@Repository
public interface LobbyRepository extends JpaRepository<Lobby, UUID> {}
