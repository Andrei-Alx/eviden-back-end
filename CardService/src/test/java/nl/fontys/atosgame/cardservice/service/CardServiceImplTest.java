package nl.fontys.atosgame.cardservice.service;

import nl.fontys.atosgame.cardservice.dto.CreateCardDto;
import nl.fontys.atosgame.cardservice.model.Card;
import nl.fontys.atosgame.cardservice.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.stream.function.StreamBridge;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardServiceImplTest {

    private CardRepository cardRepository;
    private StreamBridge streamBridge;
    private CardServiceImpl cardService;

    @BeforeEach
    void setUp() {
        cardRepository = mock(CardRepository.class);
        streamBridge = mock(StreamBridge.class);
        cardService = new CardServiceImpl(cardRepository, streamBridge);
    }

    @Test
    void createCardWithValidCard() {
        CreateCardDto createCardDto = mock(CreateCardDto.class);
        Card card = mock(Card.class);
        when(cardRepository.save(any(Card.class))).thenReturn(card);

        Card result = cardService.createCard(createCardDto);

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
}