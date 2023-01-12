package nl.fontys.atosgame.gameappbff.repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import nl.fontys.atosgame.gameappbff.enums.CardSetType;
import nl.fontys.atosgame.gameappbff.model.CardSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardSetRepository extends JpaRepository<CardSet, UUID> {
    Optional<ArrayList<CardSet>> findCardSetsByType(CardSetType type);
}
