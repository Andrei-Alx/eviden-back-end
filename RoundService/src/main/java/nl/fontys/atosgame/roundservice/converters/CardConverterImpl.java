package nl.fontys.atosgame.roundservice.converters;

import nl.fontys.atosgame.roundservice.model.Card;
import org.springframework.stereotype.Service;

/**
 * Converts a cardService.card to a roundService.card
 * @author Eli
 */
@Service
public class CardConverterImpl implements CardConverter {
    /**
     * Convert a cardService.card to a roundService.card
     *
     * @param card The card to convert
     * @return The converted card
     */
    @Override
    public Card convert(nl.fontys.atosgame.cardservice.model.Card card) {
        return new Card(card.getId(), card.getTags());
    }
}
