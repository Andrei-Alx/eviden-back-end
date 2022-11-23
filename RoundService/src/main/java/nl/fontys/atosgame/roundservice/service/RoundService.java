package nl.fontys.atosgame.roundservice.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.model.LobbySettings;
import nl.fontys.atosgame.roundservice.model.PlayerRound;
import nl.fontys.atosgame.roundservice.model.Round;

/**
 * Service for round related operations
 */
public interface RoundService {
    /**
     * Create rounds for a game
     * @param gameId The id of the game
     * @param roundSettings The settings for the rounds
     */
    List<Round> createRounds(UUID gameId, List<RoundSettingsDto> roundSettings);

    /**
     * Start a round
     * @param roundId The id of the round
     * @param playerIds the ids of the players to start the round for
     * @param gameId the id of the game
     * @return The updated round
     */
    Round startRound(UUID roundId, List<UUID> playerIds, UUID gameId);

    /**
     * End a round
     * @param roundId The id of the round
     * @param gameId The id of the game
     * @return The updated round
     */
    Round endRound(UUID roundId, UUID gameId);

    /**
     * Get a round by id
     * @param roundId The id of the round
     * @return The round
     */
    Optional<Round> getRound(UUID roundId);

    /**
     * Like a card
     * Produces an event and updates the player round
     * @param playerId The player to like the card for
     * @param roundId   The round to like the card for
     * @param cardId   The card to like
     * @return The updated round
     */
    Round likeCard(UUID playerId, UUID roundId, UUID cardId);

    /**
     * Dislike a card
     * Produces an event and updates the player round
     * @param playerId The player to dislike the card for
     * @param roundId  The round to dislike the card for
     * @param cardId  The card to dislike
     * @return
     */
    Round dislikeCard(UUID playerId, UUID roundId, UUID cardId);

    /**
     * Add cards to the selected cards of the player round
     * Produces an event and updates the round
     * @param playerId The id of the player
     * @param roundId The id of the round
     * @param cardIds The ids of the cards to select
     * @return The updated round
     */
    Round selectCards(UUID playerId, UUID roundId, List<UUID> cardIds);
}
