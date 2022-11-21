package nl.fontys.atosgame.gameappbff.repository;

import nl.fontys.atosgame.gameappbff.model.Lobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LobbyRepository extends JpaRepository<Lobby, UUID> {

}
