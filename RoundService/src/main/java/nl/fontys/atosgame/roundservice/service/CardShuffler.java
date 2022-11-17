package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.model.Card;

import java.util.Collection;
import java.util.List;

public interface CardShuffler {
    /**
     * Shuffles a list of cards completely random
     * @param cards The cards to shuffle
     * @return The shuffled cards
     */
    List<Card> randomShuffle(Collection<Card> cards);
}
