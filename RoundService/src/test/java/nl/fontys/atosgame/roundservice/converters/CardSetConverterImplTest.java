package nl.fontys.atosgame.roundservice.converters;

import nl.fontys.atosgame.roundservice.model.Card;
import nl.fontys.atosgame.roundservice.model.CardSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class CardSetConverterImplTest {

    private CardConverter cardConverter;
    private CardSetConverterImpl cardSetConverter;

    @BeforeEach
    void setUp() {
        cardConverter = mock(CardConverter.class);
        cardSetConverter = new CardSetConverterImpl(cardConverter);
    }

    @Test
    void convert() {
        doReturn(new Card()).when(cardConverter).convert(any());
        nl.fontys.atosgame.cardservice.model.CardSet cardSet = new nl.fontys.atosgame.cardservice.model.CardSet();
        cardSet.setId(UUID.randomUUID());
        cardSet.setCards(List.of(mock(nl.fontys.atosgame.cardservice.model.Card.class), mock(nl.fontys.atosgame.cardservice.model.Card.class)));

        CardSet convertedCardSet = cardSetConverter.convert(cardSet);

        assertEquals(cardSet.getId(), convertedCardSet.getId());
        assertEquals(cardSet.getCards().size(), convertedCardSet.getCards().size());
    }
}