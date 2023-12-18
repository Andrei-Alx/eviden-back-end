package nl.fontys.atosgame.gameservice.repository;

import nl.fontys.atosgame.gameservice.model.Game;
import nl.fontys.atosgame.gameservice.model.Lobby;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LobbyRepository extends JpaRepository<Lobby, UUID> {

}
