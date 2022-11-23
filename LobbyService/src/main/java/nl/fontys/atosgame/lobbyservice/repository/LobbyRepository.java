package nl.fontys.atosgame.lobbyservice.repository;

import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import nl.fontys.atosgame.lobbyservice.model.Lobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for lobbies.
 */
@Repository
public interface LobbyRepository extends JpaRepository<Lobby, UUID> {
    @Transactional
    void deleteByGameId(UUID gameId);

    Lobby getByGameId(UUID gameId);

    Lobby getByLobbyCode(String lobbyCode);

    Lobby getById(UUID lobbyId);
}
