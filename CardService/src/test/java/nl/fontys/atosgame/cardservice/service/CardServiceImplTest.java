package nl.fontys.atosgame.cardservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;
import nl.fontys.atosgame.cardservice.dto.CreateCardDto;
import nl.fontys.atosgame.cardservice.model.Card;
import nl.fontys.atosgame.cardservice.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.stream.function.StreamBridge;

class CardServiceImplTest {

    private CardRepository cardRepository;
    private StreamBridge streamBridge;
    private CardServiceImpl cardService;

    @BeforeEach
    void setUp() {
        cardRepository = mock(CardRepository.class);
        streamBridge = mock(StreamBridge.class);
        cardService = new CardServiceImpl(cardRepository);
    }

    @Test
    void createCardWithValidCard() {
        CreateCardDto createCardDto = mock(CreateCardDto.class);
        Card card = mock(Card.class);
        when(cardRepository.save(any(Card.class))).thenReturn(card);

        Card result = cardService.createCard(new Card(null, createCardDto.getTags(),createCardDto.getTranslations(), true));

        assertEquals(card, result);
        verify(cardRepository, times(1)).save(any(Card.class));
        verify(streamBridge, times(1)).send("cardCreated-in-0", card);
    }

    @Test
    void updateCardWithValidCard() {
        Card card = mock(Card.class);
        when(cardRepository.save(any(Card.class))).thenReturn(card);

        Card result = cardService.updateCard(card);

        assertEquals(card, result);
        verify(cardRepository, times(1)).save(any(Card.class));
        verify(streamBridge, times(1)).send("cardUpdated-in-0", card);
    }

    @Test
    void deleteCardWithValidId() {
        UUID id = UUID.randomUUID();
        cardService.deleteCard(id);

        verify(cardRepository, times(1)).deleteById(id);
    }

    @Test
    void getCardsByIds() {
        List<Card> cards = mock(List.class);
        List<UUID> ids = mock(List.class);
        when(cardRepository.findAllById(ids)).thenReturn(cards);

        List<Card> result = cardService.getCardsByIds(ids);

        assertEquals(cards, result);
    }
}
