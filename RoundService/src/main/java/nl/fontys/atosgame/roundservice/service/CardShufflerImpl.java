package nl.fontys.atosgame.roundservice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import nl.fontys.atosgame.roundservice.model.Card;
import org.springframework.stereotype.Service;

/**
 * Service class that shuffles cards
 *
 * @author Eli
 */
@Service
public class CardShufflerImpl implements CardShuffler {

    /**
     * Shuffles a list of cards completely random
     *
     * @param cards The cards to shuffle
     * @return The shuffled cards
     */
    @Override
    public List<Card> randomShuffle(Collection<Card> cards) {
        List<Card> shuffledCards = new ArrayList<>(cards);
        Collections.shuffle(shuffledCards);
        return shuffledCards;
    }
}
