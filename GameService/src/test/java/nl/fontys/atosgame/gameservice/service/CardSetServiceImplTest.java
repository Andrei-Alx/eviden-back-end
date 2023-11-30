package nl.fontys.atosgame.gameservice.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.UUID;
import nl.fontys.atosgame.gameservice.model.CardSet;
import nl.fontys.atosgame.gameservice.repository.CardSetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.stream.function.StreamBridge;

public class CardSetServiceImplTest {

    private CardSetRepository cardSetRepository;
    private StreamBridge streamBridge;
    private CardSetServiceImpl cardSetService;

    @BeforeEach
    void setUp() {
        cardSetRepository = mock(CardSetRepository.class);
        streamBridge = mock(StreamBridge.class);
        cardSetService = new CardSetServiceImpl(cardSetRepository, streamBridge);
    }

    @Test
    void createCardSet() {
        UUID cardSetId = UUID.randomUUID();
        CardSet cardSet = new CardSet(cardSetId);
        cardSetService.createCardSet(cardSet);

        verify(cardSetRepository).save(cardSet);
    }

    @Test
    void updateCardSet() {
        UUID cardSetId = UUID.randomUUID();
        CardSet cardSet = new CardSet(cardSetId);

        cardSetService.updateCardSet(cardSet);

        verify(cardSetRepository).save(cardSet);
    }

    @Test
    void deleteCardSet() {
        UUID cardSetId = UUID.randomUUID();
        CardSet cardSet = new CardSet(cardSetId);
        cardSetService.createCardSet(cardSet);

        cardSetService.deleteCardSet(cardSetId);

        verify(cardSetRepository).deleteById(cardSetId);
    }

    @Test
    void getCardSet() {
        UUID cardSetId = UUID.randomUUID();
        CardSet cardSet = new CardSet(cardSetId);
        cardSetService.createCardSet(cardSet);

        cardSetService.getCardSet(cardSetId);

        verify(cardSetRepository).findById(cardSetId);
    }
}
