package nl.fontys.atosgame.roundservice.converters;

import nl.fontys.atosgame.cardservice.model.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CardConverterImplTest {

    private CardConverterImpl cardConverter;

    @BeforeEach
    void setUp() {
        cardConverter = new CardConverterImpl();
    }

    @Test
    void convert() {
        nl.fontys.atosgame.cardservice.model.Card card = new nl.fontys.atosgame.cardservice.model.Card();
        card.setId(UUID.randomUUID());
        card.setTags(List.of(mock(Tag.class), mock(Tag.class)));

        nl.fontys.atosgame.roundservice.model.Card convertedCard = cardConverter.convert(card);

        assertEquals(card.getId(), convertedCard.getId());
        assertEquals(card.getTags(), convertedCard.getTags());
    }
}