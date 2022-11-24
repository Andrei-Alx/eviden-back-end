package nl.fontys.atosgame.roundservice.service;

import java.util.List;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.model.PlayerRound;
import org.springframework.stereotype.Service;

public interface PlayerRoundService {
    /**
     * Like a card
     * Produces an event and updates the player round
     *
     * @param playerRound The playerRound to like the card for
     * @param cardId      The card to like
     * @param gameId      The game id
     * @param roundId      The round id
     * @return The updated player round
     */
    PlayerRound likeCard(PlayerRound playerRound, UUID cardId, UUID gameId, UUID roundId);

    /**
     * Dislike a card
     * Produces an event and updates the player round
     *
     * @param playerRound The playerRound to dislike the card for
     * @param cardId      The card to dislike
     * @param gameId      The game id
     * @param roundId      The round id
     * @return
     */
    PlayerRound dislikeCard(PlayerRound playerRound, UUID cardId, UUID gameId, UUID roundId);

    /**
     * Add cards to the selected cards of the player round
     * Produces an event and updates the round
     *
     * @param playerRound The playerRound to selected the cards for
     * @param cardIds     The ids of the cards to select
     * @param gameId      The game id
     * @param roundId      The round id
     * @return The updated player round
     */
    PlayerRound selectCards(PlayerRound playerRound, List<UUID> cardIds, UUID gameId, UUID roundId);

    /**
     * Check if a player round is finished and sends an application event if it is
     * @param playerRound The player round to check
     */
    void checkIfPlayerRoundIsFinished(PlayerRound playerRound);
}
