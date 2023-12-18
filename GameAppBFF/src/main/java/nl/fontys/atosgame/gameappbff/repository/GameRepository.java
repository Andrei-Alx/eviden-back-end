package nl.fontys.atosgame.gameappbff.repository;

import java.util.List;
import java.util.UUID;

import nl.fontys.atosgame.gameappbff.enums.GameStatus;
import nl.fontys.atosgame.gameappbff.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID>
{
    @Query("SELECT g FROM Game g WHERE g.status != 2")
    List<Game> findByGameStatus();
}
