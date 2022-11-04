package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.model.Card;
import nl.fontys.atosgame.roundservice.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service for card related operations
 * @author Eli
 */
@Service
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;

    public CardServiceImpl(@Autowired CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * Create a new card
     * @param card The card to create
     */
    @Override
    public void createCard(Card card) {
        cardRepository.save(card);
    }

    /**
     *  Update an existing card
     * @param card The card to update
     */
    @Override
    public void updateCard(Card card) {
        cardRepository.save(card);
    }

    /**
     * Delete an existing card
     * @param uuid The id of the card to delete
     */
    @Override
    public void deleteCard(UUID uuid) {
        cardRepository.deleteById(uuid);
    }
}
