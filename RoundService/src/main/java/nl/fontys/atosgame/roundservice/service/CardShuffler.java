package nl.fontys.atosgame.roundservice.service;

import java.util.Collection;
import java.util.List;
import nl.fontys.atosgame.roundservice.model.Card;

/**
 * Service class that shuffles cards
 */
public interface CardShuffler {
    /**
     * Shuffles a list of cards completely random
     * @param cards The cards to shuffle
     * @return The shuffled cards
     */
    List<Card> randomShuffle(Collection<Card> cards);
}
