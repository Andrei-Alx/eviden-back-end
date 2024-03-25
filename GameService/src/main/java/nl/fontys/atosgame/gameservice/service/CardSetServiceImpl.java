package nl.fontys.atosgame.gameservice.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import nl.fontys.atosgame.gameservice.event.produced.CardSetRequestEvent;
import nl.fontys.atosgame.gameservice.model.CardSet;
import nl.fontys.atosgame.gameservice.repository.CardSetRepository;
import nl.fontys.atosgame.gameservice.service.interfaces.CardSetService;
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
        cardSetRepository.saveAndFlush(cardSet);
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
     * Get a cardSet from the database.
     *
     * @param cardSetId The id of the cardSet.
     * @return The cardSet.
     */
    @Override
    public Optional<CardSet> getCardSetById(UUID cardSetId) {
        return cardSetRepository.findById(cardSetId);
    }

    public List<CardSet> getAllCardSets() {
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
