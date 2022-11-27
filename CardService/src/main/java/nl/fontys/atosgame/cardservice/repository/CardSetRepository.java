package nl.fontys.atosgame.cardservice.repository;

import java.util.UUID;
import nl.fontys.atosgame.cardservice.model.CardSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardSetRepository extends JpaRepository<CardSet, UUID> {}
