package nl.fontys.atosgame.roundservice.repository;

import nl.fontys.atosgame.roundservice.model.PlayerRound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlayerRoundRepository extends JpaRepository<PlayerRound, UUID> {
}
