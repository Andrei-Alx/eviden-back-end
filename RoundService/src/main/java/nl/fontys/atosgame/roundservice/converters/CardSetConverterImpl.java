package nl.fontys.atosgame.roundservice.converters;

import nl.fontys.atosgame.roundservice.model.Card;
import nl.fontys.atosgame.roundservice.model.CardSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CardSetConverterImpl implements CardSetConverter {

    private CardConverter cardConverter;

    public CardSetConverterImpl(@Autowired CardConverter cardConverter) {
        this.cardConverter = cardConverter;
    }

    /**
     * Convert a cardService.cardSet to a roundService.cardSet
     *
     * @param cardSet The cardSet to convert
     * @return The converted cardSet
     */
    @Override
    public CardSet convert(nl.fontys.atosgame.cardservice.model.CardSet cardSet) {
        Collection<Card> cards = cardSet.getCards().stream().map(cardConverter::convert).collect(Collectors.toList());
        return new CardSet(cardSet.getId(), cards);
    }
}
