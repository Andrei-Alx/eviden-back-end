package nl.fontys.atosgame.gameappbff.repository;

import java.util.UUID;
import nl.fontys.atosgame.gameappbff.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {}
