package nl.fontys.atosgame.roundservice.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import nl.fontys.atosgame.roundservice.enums.PlayerRoundPhase;
import nl.fontys.atosgame.roundservice.model.Card;
import nl.fontys.atosgame.roundservice.model.PlayerRound;

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
    PlayerRound dislikeCard(
        PlayerRound playerRound,
        UUID cardId,
        UUID gameId,
        UUID roundId
    );

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
    PlayerRound selectCards(
        PlayerRound playerRound,
        List<UUID> cardIds,
        UUID gameId,
        UUID roundId
    );

    /**
     * Check if a player round is finished and sends an application event if it is
     * @param playerRound The player round to check
     */
    void endPlayerRound(PlayerRound playerRound);

    /**
     * Check if a playerRound is done.
     * A playerRound is done when the player has liked and picked enough cards and has a determinate result.
     * @param playerRound The player round to check
     * @return True if the playerRound is done, false otherwise.
     */
    boolean playerRoundIsDone(PlayerRound playerRound);

    /**
     * Get the current phase of the playerRound.
     * @return The current phase of the playerRound.
     * @param playerRound The player round to check
     */
    PlayerRoundPhase playerRoundGetPhase(PlayerRound playerRound);

    /**
     * Get the results per color of the playerRound.
     * @param playerRound The player round to check
     * @return The results per color of the playerRound.
     */
    Map<String, Integer> determineCardsChosenPerType(PlayerRound playerRound);

    /**
     * Get a list of card types for a player round
     * @param playerRound The player round to check
     * @param tagCount
     * @return A list of Card Types
     */
    List<String> getTopResultCardTypes(PlayerRound playerRound, Map<String, Integer> tagCount);

    /**
     * Add a card to the likedCards list.
     * Checks if the card is in the hand of the player and if the card is not already liked.
     * @param playerRound The player round to check
     * @param card
     */
    void addLikedCard(PlayerRound playerRound, Card card);

    /**
     * Add a card to the selectedCards list.
     * Checks if the cards are in the hand and if the player has not already picked the cards
     * @param playerRound The player round to check
     * @param card The card to add.
     */
    void addSelectedCards(PlayerRound playerRound, List<Card> card);

    /**
     * Add a card to the disliked list.
     * Checks if the card is in the hand of the player and if the card is not already disliked.
     * @param playerRound The player round to check
     * @param card The card to add.
     */
    void addDislikedCard(PlayerRound playerRound, Card card);
}
