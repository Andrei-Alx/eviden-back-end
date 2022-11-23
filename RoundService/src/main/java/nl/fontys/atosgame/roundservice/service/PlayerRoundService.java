package nl.fontys.atosgame.roundservice.service;

import java.util.List;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.model.PlayerRound;
import org.springframework.stereotype.Service;

public interface PlayerRoundService {
    /**
     * Like a card
     * Produces an event and updates the player round
     * @param playerId The player to like the card for
     * @param roundId   The round to like the card for
     * @param cardId   The card to like
     * @return The updated player round
     */
    PlayerRound likeCard(UUID playerId, UUID roundId, UUID cardId);

    /**
     * Dislike a card
     * Produces an event and updates the player round
     * @param playerId The player to dislike the card for
     * @param roundId  The round to dislike the card for
     * @param cardId  The card to dislike
     * @return
     */
    PlayerRound dislikeCard(UUID playerId, UUID roundId, UUID cardId);

    /**
     * Add cards to the selected cards of the player round
     * Produces an event and updates the round
     * @param playerId The id of the player
     * @param roundId The id of the round
     * @param cardIds The ids of the cards to select
     * @return The updated player round
     */
    PlayerRound selectCards(UUID playerId, UUID roundId, List<UUID> cardIds);

    /**
     * Get the player round for the given player and round
     * @param playerId The player id
     * @param roundId The round id
     * @return The player round
     */
    PlayerRound getPlayerRound(UUID playerId, UUID roundId);
}
