package nl.fontys.atosgame.cardservice.repository;

import nl.fontys.atosgame.cardservice.model.CardSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardSetRepository extends JpaRepository<CardSet, UUID> {
}
