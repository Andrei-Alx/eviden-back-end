package nl.fontys.atosgame.roundservice.repository;

import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for game related operations
 * @author Eli
 */
@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {
    Optional<Game> getGameByRoundsId(UUID roundId);
}
