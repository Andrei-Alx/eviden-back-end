package nl.fontys.atosgame.gameappbff.repository;

import java.util.UUID;
import nl.fontys.atosgame.gameappbff.model.Round;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundRepository extends JpaRepository<Round, UUID> {}
