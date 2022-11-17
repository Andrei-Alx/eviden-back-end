package nl.fontys.atosgame.gameservice.service;

import nl.fontys.atosgame.gameservice.model.CardSet;
import nl.fontys.atosgame.gameservice.repository.CardSetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.stream.function.StreamBridge;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CardSetServiceImplTest {

    private CardSetRepository cardSetRepository;
    private StreamBridge streamBridge;
    private CardSetServiceImpl cardSetService;

    @BeforeEach
    void setUp() {
        cardSetRepository = mock(CardSetRepository.class);
        streamBridge = mock(StreamBridge.class);
        cardSetService = new CardSetServiceImpl(cardSetRepository);
    }

    @Test
    void createCardSet() {
        UUID cardSetId = UUID.randomUUID();
        CardSet cardSet = new CardSet(cardSetId);
        cardSetService.handleCardSetCreated(cardSet);

        verify(cardSetRepository).save(cardSet);
    }

    @Test
    void updateCardSet(){
        UUID cardSetId = UUID.randomUUID();
        CardSet cardSet = new CardSet(cardSetId);

        cardSetService.handleCardSetUpdated(cardSet);

        verify(cardSetRepository).save(cardSet);
    }

    @Test
    void deleteCardSet(){
        UUID cardSetId = UUID.randomUUID();
        CardSet cardSet = new CardSet(cardSetId);
        cardSetService.handleCardSetCreated(cardSet);

        cardSetService.handleCardSetDeleted(cardSetId);

        verify(cardSetRepository).deleteById(cardSetId);
    }
}
