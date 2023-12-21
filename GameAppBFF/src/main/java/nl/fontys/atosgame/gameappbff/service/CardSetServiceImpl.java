package nl.fontys.atosgame.gameappbff.service;

import java.util.*;

import jakarta.persistence.EntityNotFoundException;
import nl.fontys.atosgame.gameappbff.event.produced.CardSetRequestEvent;
import nl.fontys.atosgame.gameappbff.model.CardSet;
import nl.fontys.atosgame.gameappbff.model.Tag;
import nl.fontys.atosgame.gameappbff.repository.CardSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

/**
 * Service for handling cardsets.
 * @author Aniek
 */
@Service
public class CardSetServiceImpl implements CardSetService {

    private final CardSetRepository cardSetRepository;

    private final StreamBridge streamBridge;

    public CardSetServiceImpl(@Autowired CardSetRepository cardSetRepository, @Autowired StreamBridge streamBridge) {
        this.cardSetRepository = cardSetRepository;
        this.streamBridge = streamBridge;
    }

    /**
     * Create a new cardset in the database.
     *
     * @param cardSet The cardset to create.
     */
    @Override
    public void createCardSet(CardSet cardSet) {
        cardSetRepository.save(cardSet);
    }

    /**
     * Update a cardset in the database.
     *
     * @param cardSet The cardset to update.
     */
    @Override
    public void updateCardSet(CardSet cardSet) {
        cardSetRepository.save(cardSet);
    }

    /**
     * Delete a cardset from the database.
     *
     * @param cardSetId The id of the cardSet to delete.
     */
    @Override
    public void deleteCardSet(UUID cardSetId) {
        cardSetRepository.deleteById(cardSetId);
    }

    /**
     * Get cardsets from the database by type.
     *
     * @param cardSetType The type of the cardSet to get.
     * @return A list of cardsets.
     */
    @Override
    public Optional<ArrayList<CardSet>> getCardSetsByType(Tag typeTag) {
        return cardSetRepository.findCardSetsByTagsContains(typeTag);
    }

    @Override
    public List<CardSet> getAllCardSets() throws EntityNotFoundException {
        return cardSetRepository.findAll();
    }

    @Override
    public void cardSetRequest() {
        streamBridge.send(
                "produceCardSetRequest-out-0",
                new CardSetRequestEvent()
        );
    }
}
