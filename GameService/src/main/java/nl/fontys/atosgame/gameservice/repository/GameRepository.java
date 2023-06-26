package nl.fontys.atosgame.gameservice.repository;

import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.gameservice.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {
    Optional<Game> findByRoundsId(UUID roundId);
}
