package nl.fontys.atosgame.gameservice.service;

import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.gameservice.model.CardSet;
import nl.fontys.atosgame.gameservice.repository.CardSetRepository;
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

    /**
     * Get a cardSet from the database.
     *
     * @param cardSetId The id of the cardSet.
     * @return The cardSet.
     */
    @Override
    public Optional<CardSet> getCardSet(UUID cardSetId) {
        return cardSetRepository.findById(cardSetId);
    }
}
