package nl.fontys.atosgame.roundservice.service;

import java.util.UUID;
import nl.fontys.atosgame.roundservice.model.Card;

public interface CardService {
    /**
     * Create a new card
     * @param card The card to create
     */
    void createCard(Card card);
    /**
     * Update an existing card
     * @param card The card to update
     */
    void updateCard(Card card);

    /**
     * Delete an existing card
     * @param uuid The id of the card to delete
     */
    void deleteCard(UUID uuid);
}
