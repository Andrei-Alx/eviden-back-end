package nl.fontys.atosgame.gameappbff.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.gameappbff.enums.CardSetType;
import nl.fontys.atosgame.gameappbff.model.CardSet;
import nl.fontys.atosgame.gameappbff.model.Tag;

import javax.persistence.EntityNotFoundException;

/**
 * Service for handling cardsets.
 * @author Aniek
 */
public interface CardSetService {
    /**
     * Create a new cardset in the database.
     * @param cardSet The cardset to create.
     */
    void createCardSet(CardSet cardSet);

    /**
     * Update a cardSet in the database.
     * @param cardSet The card to update.
     */
    void updateCardSet(CardSet cardSet);

    /**
     * Delete a cardSet from the database.
     * @param cardSetId The id of the card to delete.
     */
    void deleteCardSet(UUID cardSetId);

    /**
     * Get cardSets from the database by type.
     * @param cardSetType The type of the card to get.
     * @return A list of cardSets.
     */
    Optional<ArrayList<CardSet>> getCardSetsByType(Tag typeTag);

    List<CardSet> getAllCardSets() throws EntityNotFoundException;

    void cardSetRequest();
}
