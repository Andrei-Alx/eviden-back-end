package nl.fontys.atosgame.gameappbff.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.gameappbff.model.Card;

/**
 * Service for handling cards.
 * @author Eli
 */
public interface CardService {
    /**
     * Create a new card in the database.
     * @param card The card to create.
     */
    void handleCardCreated(Card card);

    /**
     * Update a card in the database.
     * @param card The card to update.
     */
    void handleCardUpdated(Card card);

    /**
     * Delete a card from the database.
     * @param cardId The id of the card to delete.
     */
    void handleCardDeleted(UUID cardId);

    /**
     * Get a list card by ids
     * @param cardIds The ids of the cards
     * @return The list of cards
     */
    List<Card> getCards(List<UUID> cardIds);

    /**
     * Get a card by id
     * @param cardId The id of the card
     * @return The card
     */
    Optional<Card> getCard(UUID cardId);
}
