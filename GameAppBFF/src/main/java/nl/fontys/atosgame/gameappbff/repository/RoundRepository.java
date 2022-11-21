package nl.fontys.atosgame.gameappbff.repository;

import nl.fontys.atosgame.gameappbff.model.Round;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoundRepository extends JpaRepository<Round, UUID> {
}
