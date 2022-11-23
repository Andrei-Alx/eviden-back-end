package nl.fontys.atosgame.roundservice.service;

import java.util.List;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.model.PlayerRound;
import org.springframework.stereotype.Service;

public interface PlayerRoundService {
    /**
     * Like a card
     * Produces an event and updates the player round
     * @param playerRound The playerRound to like the card for
     * @param cardId   The card to like
     * @return The updated player round
     */
    PlayerRound likeCard(PlayerRound playerRound, UUID cardId);

    /**
     * Dislike a card
     * Produces an event and updates the player round
     * @param playerRound The playerRound to dislike the card for
     * @param cardId  The card to dislike
     * @return
     */
    PlayerRound dislikeCard(PlayerRound playerRound, UUID cardId);

    /**
     * Add cards to the selected cards of the player round
     * Produces an event and updates the round
     * @param playerRound The playerRound to selected the cards for
     * @param cardIds The ids of the cards to select
     * @return The updated player round
     */
    PlayerRound selectCards(PlayerRound playerRound, List<UUID> cardIds);
}
