package nl.fontys.atosgame.roundservice.service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import nl.fontys.atosgame.roundservice.model.Card;
import nl.fontys.atosgame.roundservice.model.PlayerRound;
import nl.fontys.atosgame.roundservice.model.Round;
import nl.fontys.atosgame.roundservice.repository.PlayerRoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class PlayerRoundServiceImpl implements PlayerRoundService {

    private RoundService roundService;
    private CardService cardService;
    private PlayerRoundRepository playerRoundRepository;

    public PlayerRoundServiceImpl(
            @Autowired RoundService roundService,
            @Autowired CardService cardService,
            @Autowired PlayerRoundRepository playerRoundRepository
    ) {
        this.roundService = roundService;
        this.cardService = cardService;
        this.playerRoundRepository = playerRoundRepository;
    }

    /**
     * Like a card
     * Produces an event and updates the player round
     *
     * @param playerId The player to like the card for
     * @param roundId  The round to like the card for
     * @param cardId   The card to like
     * @return The updated player round
     */
    @Override
    public PlayerRound likeCard(UUID playerId, UUID roundId, UUID cardId) {
        PlayerRound playerRound = getPlayerRound(playerId, roundId);
        Card card = this.cardService.getCard(cardId).get();
        playerRound.addLikedCard(card);
        playerRound = playerRoundRepository.save(playerRound);

        // TODO: produce event
        return playerRound;
    }

    /**
     * Dislike a card
     * Produces an event and updates the player round
     *
     * @param playerId The player to dislike the card for
     * @param roundId  The round to dislike the card for
     * @param cardId   The card to dislike
     * @return
     */
    @Override
    public PlayerRound dislikeCard(UUID playerId, UUID roundId, UUID cardId) {
        PlayerRound playerRound = getPlayerRound(playerId, roundId);
        Card card = this.cardService.getCard(cardId).get();
        playerRound.addDislikedCard(card);
        playerRound = playerRoundRepository.save(playerRound);

        // TODO: produce event
        return playerRound;
    }

    /**
     * Add cards to the selected cards of the player round
     * Produces an event and updates the round
     *
     * @param playerId The id of the player
     * @param roundId  The id of the round
     * @param cardIds  The ids of the cards to select
     * @return The updated player round
     */
    @Override
    public PlayerRound selectCards(UUID playerId, UUID roundId, List<UUID> cardIds) {
        PlayerRound playerRound = getPlayerRound(playerId, roundId);
        Collection<Card> cards = this.cardService.getCards(cardIds);
        playerRound.addSelectedCards(List.copyOf(cards));
        playerRound = playerRoundRepository.save(playerRound);

        // TODO: produce event
        return playerRound;
    }

    /**
     * Get the player round for the given player and round
     *
     * @param playerId The player id
     * @param roundId  The round id
     * @return The player round
     */
    @Override
    public PlayerRound getPlayerRound(UUID playerId, UUID roundId) {
        Round round = roundService.getRound(roundId).get();
        return round.getPlayerRounds().stream()
                .filter(playerRound -> playerRound.getPlayerId().equals(playerId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Player round not found"));
    }
}
