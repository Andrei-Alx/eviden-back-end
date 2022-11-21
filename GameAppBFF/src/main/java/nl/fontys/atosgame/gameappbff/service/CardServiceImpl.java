package nl.fontys.atosgame.gameappbff.service;

import java.util.List;
import java.util.UUID;
import nl.fontys.atosgame.gameappbff.model.Card;
import nl.fontys.atosgame.gameappbff.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for handling cards.
 * @author Eli
 */
@Service
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;

    public CardServiceImpl(@Autowired CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * Create a new card in the database.
     *
     * @param card The card to create.
     */
    @Override
    public void handleCardCreated(Card card) {
        cardRepository.save(card);
    }

    /**
     * Update a card in the database.
     *
     * @param card The card to update.
     */
    @Override
    public void handleCardUpdated(Card card) {
        cardRepository.save(card);
    }

    /**
     * Delete a card from the database.
     *
     * @param cardId The id of the card to delete.
     */
    @Override
    public void handleCardDeleted(UUID cardId) {
        cardRepository.deleteById(cardId);
    }

    /**
     * Get a list card by ids
     *
     * @param cardIds The ids of the cards
     * @return The list of cards
     */
    @Override
    public List<Card> getCards(List<UUID> cardIds) {
        return cardRepository.findAllById(cardIds);
    }
}
