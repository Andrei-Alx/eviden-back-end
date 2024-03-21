package nl.fontys.atosgame.gameappbff.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.gameappbff.controller.GameSocketController;
import nl.fontys.atosgame.gameappbff.model.Card;
import nl.fontys.atosgame.gameappbff.model.PlayerRound;
import nl.fontys.atosgame.gameappbff.model.Round;
import nl.fontys.atosgame.gameappbff.repository.PlayerRoundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that manages playerRounds
 *
 * @author Eli
 */
@Service
public class PlayerRoundServiceImpl implements PlayerRoundService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerRoundServiceImpl.class);
    private PlayerRoundRepository playerRoundRepository;
    private RoundService roundService;
    private CardService cardService;

    private GameSocketController gameSocketController;

    public PlayerRoundServiceImpl(
        @Autowired PlayerRoundRepository playerRoundRepository,
        @Autowired RoundService roundService,
        @Autowired GameSocketController gameSocketController,
        @Autowired CardService cardService
    ) {
        this.playerRoundRepository = playerRoundRepository;
        this.roundService = roundService;
        this.gameSocketController = gameSocketController;
        this.cardService = cardService;
    }

    /**
     * Create a playerRound
     *
     * @param playerId The id of the player
     * @param roundId  The id of the round
     * @return The created playerRound
     */
    @Override
    public PlayerRound createPlayerRound(UUID playerId, UUID roundId) {
        PlayerRound playerRound = new PlayerRound(
            null,
            playerId,
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        );
        playerRound = playerRoundRepository.save(playerRound);
        roundService.addPlayerRound(roundId, playerRound);
        return playerRound;
    }

    /**
     * Starts a playerRound. Handles creating a playerRound if it doesn't exist yet
     * @param playerId The id of the player
     * @param roundId The id of the round
     * @param gameId The id of the game
     * @param phaseNumber The phase number
     * @return The updated playerRound
     */
    @Override
    public PlayerRound startPhase(
        UUID playerId,
        UUID roundId,
        UUID gameId,
        int phaseNumber
    ) {
        Round round = roundService.getRoundById(roundId).get();
        // Find the playerRound for the player or create a new one
        Optional<PlayerRound> playerRoundOptional = getPlayerRound(playerId, roundId);
        PlayerRound playerRound;
        if (playerRoundOptional.isEmpty()) {
            playerRound = createPlayerRound(playerId, roundId);
            round.addPlayerRound(playerRound);
        } else {
            playerRound = playerRoundOptional.get();
        }

        // Set the phase number
        // TODO: Should there be some signifier for the phase here?

        // Send to socket
        gameSocketController.playerPhase(gameId, phaseNumber, playerRound);
        return playerRoundRepository.save(playerRound);
    }

    /**
     * Ends a playerRound
     *
     * @param playerId    The id of the player
     * @param roundId     The id of the round
     * @param gameId      The id of the game
     * @param phaseNumber The phase number
     * @return The updated playerRound
     */
    @Override
    public PlayerRound endPhase(
        UUID playerId,
        UUID roundId,
        UUID gameId,
        int phaseNumber
    ) {
        // Get the playerRound
        PlayerRound playerRound = getPlayerRound(playerId, roundId).get();

        // Set the phase number
        // TODO: Should there be some signifier for the phase here?

        //        // Send to socket
        //        gameSocketController.playerPhase(gameId, playerId, phaseNumber);
        return playerRoundRepository.save(playerRound);
    }

    /**
     * Gets a playerRound
     *
     * @param playerId The id of the player
     * @param roundId  The id of the round
     * @return The playerRound
     */
    @Override
    public Optional<PlayerRound> getPlayerRound(UUID playerId, UUID roundId) {
        Round round = roundService.getRoundById(roundId).get();
        return round
            .getPlayerRounds()
            .stream()
            .filter(pr -> pr.getPlayerId().equals(playerId))
            .findFirst();
    }

    /**
     * Add cards to distributed cards
     *
     * @param playerId The id of the player
     * @param roundId  The id of the round
     * @param gameId   The id of the game
     * @param cardIds  The ids of the cards
     * @return The updated playerRound
     */
    @Override
    public PlayerRound distributeCards(
        UUID playerId,
        UUID roundId,
        UUID gameId,
        List<UUID> cardIds
    ) {
        PlayerRound playerRound = getPlayerRound(playerId, roundId).get();

        // Add the cards to the distributed cards
        List<Card> cards = cardService.getCards(cardIds);
        playerRound.setDistributedCards(cards);

        // Send to socket
        gameSocketController.cardsDistributed(gameId, playerId, cards);

        return playerRoundRepository.save(playerRound);
    }

    /**
     * Add cards to liked cards
     *
     * @param playerId
     * @param roundId
     * @param gameId
     * @param cardId
     * @return The updated playerRound
     */
    @Override
    public PlayerRound likeCard(UUID playerId, UUID roundId, UUID gameId, UUID cardId) {
        PlayerRound playerRound = getPlayerRound(playerId, roundId).get();

        // Add the card to the liked cards
        Card card = cardService.getCard(cardId).get();
        playerRound.addLikedCard(card);

        LOGGER.info(String.format("liking card event => %s", playerRound));
        // Send to socket
        gameSocketController.cardLiked(gameId, playerId, card);
        return playerRoundRepository.save(playerRound);
    }

    /**
     * Add cards to disliked cards
     *
     * @param playerId The id of the player
     * @param roundId The id of the round
     * @param gameId The id of the game
     * @param cardId The id of the card
     * @return The updated playerRound
     */
    @Override
    public PlayerRound dislikeCard(
        UUID playerId,
        UUID roundId,
        UUID gameId,
        UUID cardId
    ) {
        PlayerRound playerRound = getPlayerRound(playerId, roundId).get();

        // Add the card to the disliked cards
        Card card = cardService.getCard(cardId).get();
        playerRound.addDislikedCard(card);

        // Send to socket
        gameSocketController.cardDisliked(gameId, playerId, card);
        return playerRoundRepository.save(playerRound);
    }

    /**
     * Add cards to picked cards
     *
     * @param playerId The id of the player
     * @param roundId  The id of the round
     * @param gameId   The id of the game
     * @param cardIds  The ids of the cards
     * @return The updated playerRound
     */
    @Override
    public PlayerRound selectCards(
        UUID playerId,
        UUID roundId,
        UUID gameId,
        List<UUID> cardIds
    ) {
        PlayerRound playerRound = getPlayerRound(playerId, roundId).get();

        // Add the card to the disliked cards
        List<Card> cards = cardService.getCards(cardIds);
        playerRound.addSelectedCards(cards);

        // Send to socket
        gameSocketController.cardsSelected(gameId, playerId, cards);
        return playerRoundRepository.save(playerRound);
    }
}
