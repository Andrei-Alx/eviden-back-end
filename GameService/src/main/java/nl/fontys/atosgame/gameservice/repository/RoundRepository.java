package nl.fontys.atosgame.gameservice.repository;

import nl.fontys.atosgame.gameservice.model.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoundRepository extends JpaRepository<Round, UUID> {
}
