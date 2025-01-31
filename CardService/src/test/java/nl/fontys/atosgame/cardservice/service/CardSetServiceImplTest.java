package nl.fontys.atosgame.cardservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import nl.fontys.atosgame.cardservice.dto.CreateCardSetDto;
import nl.fontys.atosgame.cardservice.model.Card;
import nl.fontys.atosgame.cardservice.model.CardSet;
import nl.fontys.atosgame.cardservice.model.Tag;
import nl.fontys.atosgame.cardservice.repository.CardSetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.stream.function.StreamBridge;

class CardSetServiceImplTest {

    private CardSetRepository cardSetRepository;
    private CardService cardService;
    private StreamBridge streamBridge;
    private CardSetServiceImpl cardSetService;

    @BeforeEach
    void setUp() {
        cardSetRepository = mock(CardSetRepository.class);
        cardService = mock(CardService.class);
        streamBridge = mock(StreamBridge.class);
        cardSetService =
            new CardSetServiceImpl(cardSetRepository, cardService, streamBridge);
    }

    @Test
    void createCardSetWithValidCardSet() {
        List<Tag> tags = new ArrayList<>();
        CreateCardSetDto createCardSetDto = new CreateCardSetDto(
            "RoundOneCards",
            tags,
            null
        );
        Collection<Card> cards = mock(Collection.class);
        doReturn(cards).when(cardService).getCardsByIds(createCardSetDto.getCards());
        CardSet cardSet = new CardSet(UUID.randomUUID(), "RoundOneCards", cards, tags, true);
        doReturn(cardSet).when(cardSetRepository).save(any());

        CardSet result = cardSetService.createCardSet(createCardSetDto);

        assertEquals(cardSet, result);
        verify(streamBridge).send("cardSetCreated-in-0", result);
    }

    @Test
    void deleteCardSet() {
        UUID id = UUID.randomUUID();

        cardSetService.deleteCardSet(id);

        verify(streamBridge).send("cardSetDeleted-in-0", id);
    }

    @Test
    void updateCardSet() {
        CardSet cardSet = new CardSet(UUID.randomUUID(), "RoundOneCards", null, null, true);
        doReturn(cardSet).when(cardSetRepository).save(any());

        CardSet result = cardSetService.updateCardSet(cardSet);

        assertEquals(cardSet, result);
        verify(streamBridge).send("cardSetUpdated-in-0", result);
    }
}
