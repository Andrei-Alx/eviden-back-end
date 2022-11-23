package nl.fontys.atosgame.gameappbff.service;

import java.util.UUID;
import nl.fontys.atosgame.gameappbff.model.CardSet;
import nl.fontys.atosgame.gameappbff.repository.CardSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for handling cardsets.
 * @author Aniek
 */
@Service
public class CardSetServiceImpl implements CardSetService {

    private CardSetRepository cardSetRepository;

    public CardSetServiceImpl(@Autowired CardSetRepository cardSetRepository) {
        this.cardSetRepository = cardSetRepository;
    }

    /**
     * Create a new cardset in the database.
     *
     * @param cardSet The cardset to create.
     */
    @Override
    public void handleCardSetCreated(CardSet cardSet) {
        cardSetRepository.save(cardSet);
    }

    /**
     * Update a cardset in the database.
     *
     * @param cardSet The cardset to update.
     */
    @Override
    public void handleCardSetUpdated(CardSet cardSet) {
        cardSetRepository.save(cardSet);
    }

    /**
     * Delete a cardset from the database.
     *
     * @param cardSetId The id of the cardSet to delete.
     */
    @Override
    public void handleCardSetDeleted(UUID cardSetId) {
        cardSetRepository.deleteById(cardSetId);
    }
}
