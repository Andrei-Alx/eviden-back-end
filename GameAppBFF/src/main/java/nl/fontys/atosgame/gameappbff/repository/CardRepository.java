package nl.fontys.atosgame.gameappbff.repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import nl.fontys.atosgame.gameappbff.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {}
