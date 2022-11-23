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
     * @param cardId  The card to like
     * @param gameId The game id
     * @param roundId The round id
     * @return The updated player round
     */
    Round likeCard(UUID playerId, UUID cardId, UUID gameId, UUID roundId);

    /**
     * Dislike a card
     * Produces an event and updates the player round
     * @param playerId The player to dislike the card for
     * @param cardId The card to dislike
     * @param gameId The game id
     * @param roundId The round id
     * @return
     */
    Round dislikeCard(UUID playerId, UUID cardId, UUID gameId, UUID roundId);

    /**
     * Add cards to the selected cards of the player round
     * Produces an event and updates the round
     * @param playerId The id of the player
     * @param cardIds The ids of the cards to select
     * @param gameId The id of the game
     * @param roundId The id of the round
     * @return The updated round
     */
    Round selectCards(UUID playerId, List<UUID> cardIds, UUID gameId, UUID roundId);
}
