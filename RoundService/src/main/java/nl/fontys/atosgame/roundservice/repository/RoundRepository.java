package nl.fontys.atosgame.roundservice.repository;

import nl.fontys.atosgame.roundservice.model.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoundRepository extends JpaRepository<Round, UUID> {
}
