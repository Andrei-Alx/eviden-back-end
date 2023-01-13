package nl.fontys.atosgame.gameappbff.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import nl.fontys.atosgame.gameappbff.model.Card;
import nl.fontys.atosgame.gameappbff.model.CardSet;
import nl.fontys.atosgame.gameappbff.model.Tag;
import nl.fontys.atosgame.gameappbff.repository.CardSetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.stream.function.StreamBridge;

class CardSetServiceImplTest {

    CardSetRepository cardSetRepository;
    StreamBridge streamBridge;
    CardSetServiceImpl cardSetService;

    @BeforeEach
    void setUp() {
        cardSetRepository = mock(CardSetRepository.class);
        streamBridge = mock(StreamBridge.class);
        cardSetService = new CardSetServiceImpl(cardSetRepository);
    }

    @Test
    void createCardSet() {
        UUID cardSetId = UUID.randomUUID();
        String name = "testCardSet";
        Collection<Card> cards = new ArrayList<>();
        Collection<Tag> tags = new ArrayList<>();
        CardSet cardSet = new CardSet(cardSetId, name, cards, tags);

        cardSetService.handleCardSetCreated(cardSet);

        verify(cardSetRepository).save(cardSet);
    }

    @Test
    void updateCardSet() {
        UUID cardSetId = UUID.randomUUID();
        String name = "testCardSet";
        String type = "testType";
        Collection<Card> cards = new ArrayList<>();
        Collection<Tag> tags = new ArrayList<>();
        CardSet cardSet = new CardSet(cardSetId, name, cards, tags);

        cardSetService.handleCardSetUpdated(cardSet);

        verify(cardSetRepository).save(cardSet);
    }

    @Test
    void deleteCardSet() {
        UUID cardSetId = UUID.randomUUID();
        String name = "testCardSet";
        String type = "testType";
        Collection<Card> cards = new ArrayList<>();
        Collection<Tag> tags = new ArrayList<>();
        CardSet cardSet = new CardSet(cardSetId, name, cards, tags);
        cardSetService.handleCardSetCreated(cardSet);

        cardSetService.handleCardSetDeleted(cardSetId);

        verify(cardSetRepository).deleteById(cardSetId);
    }
}
