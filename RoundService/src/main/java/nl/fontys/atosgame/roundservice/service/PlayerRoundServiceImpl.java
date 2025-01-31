package nl.fontys.atosgame.roundservice.service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.applicationevents.PlayerRoundFinishedAppEvent;
import nl.fontys.atosgame.roundservice.dto.*;
import nl.fontys.atosgame.roundservice.enums.PlayerRoundPhase;
import nl.fontys.atosgame.roundservice.model.Card;
import nl.fontys.atosgame.roundservice.model.PlayerRound;
import nl.fontys.atosgame.roundservice.repository.PlayerRoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Service for player round related operations
 * @author Eli
 */
@Service
public class PlayerRoundServiceImpl implements PlayerRoundService {

    private CardService cardService;
    private StreamBridge streamBridge;
    private ApplicationEventPublisher eventPublisher;
    private PlayerRoundRepository playerRoundRepository;

    public PlayerRoundServiceImpl(
        @Autowired CardService cardService,
        @Autowired StreamBridge streamBridge,
        @Autowired ApplicationEventPublisher eventPublisher,
        @Autowired PlayerRoundRepository playerRoundRepository
    ) {
        this.cardService = cardService;
        this.streamBridge = streamBridge;
        this.eventPublisher = eventPublisher;
        this.playerRoundRepository = playerRoundRepository;
    }

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
    @Override
    public PlayerRound likeCard(
        PlayerRound playerRound,
        UUID cardId,
        UUID gameId,
        UUID roundId
    ) {
        Card card = this.cardService.getCard(cardId).get();
        PlayerRoundPhase previousPhase = playerRound.getPhase();
        playerRound.addLikedCard(card);
        PlayerRoundPhase currentPhase = playerRound.getPhase();
        playerRound = playerRoundRepository.save(playerRound);

        // Send event
        streamBridge.send(
            "producePlayerLikedCard-in-0",
            new CardLikedEventDto(playerRound.getPlayerId(), gameId, roundId, cardId)
        );

        // check if phase has ended
        if (previousPhase != currentPhase) {
            streamBridge.send(
                "producePlayerPhaseEnded-in-0",
                new PlayerPhaseEndedDto(
                    previousPhase.ordinal(),
                    playerRound.getPlayerId(),
                    gameId,
                    roundId
                )
            );
            streamBridge.send(
                "producePlayerPhaseStarted-in-0",
                new PlayerPhaseStartedDto(
                    currentPhase.ordinal(),
                    playerRound.getPlayerId(),
                    gameId,
                    roundId
                )
            );
        }

        return playerRound;
    }

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
    @Override
    public PlayerRound dislikeCard(
        PlayerRound playerRound,
        UUID cardId,
        UUID gameId,
        UUID roundId
    ) {
        Card card = this.cardService.getCard(cardId).get();
        playerRound.addDislikedCard(card);
        playerRound = playerRoundRepository.save(playerRound);

        streamBridge.send(
            "producePlayerDislikedCard-in-0",
            new CardDislikedEventDto(playerRound.getPlayerId(), gameId, roundId, cardId)
        );
        return playerRound;
    }

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
    @Override
    public PlayerRound selectCards(
        PlayerRound playerRound,
        List<UUID> cardIds,
        UUID gameId,
        UUID roundId
    ) {
        Collection<Card> cards = this.cardService.getCards(cardIds);
        PlayerRoundPhase previousPhase = playerRound.getPhase();
        playerRound.addSelectedCards(List.copyOf(cards));
        PlayerRoundPhase currentPhase = playerRound.getPhase();
        playerRound = playerRoundRepository.save(playerRound);

        // Send event
        streamBridge.send(
            "producePlayerSelectedCards-in-0",
            new CardsSelectedEventDto(playerRound.getPlayerId(), cardIds, roundId, gameId)
        );

        // Check if playerRound is finished
        if (playerRound.isDone()) {
            this.endPlayerRound(playerRound);
        }

        if (previousPhase != currentPhase) {
            streamBridge.send(
                "producePlayerPhaseEnded-in-0",
                new PlayerPhaseEndedDto(
                    previousPhase.ordinal(),
                    playerRound.getPlayerId(),
                    gameId,
                    roundId
                )
            );
            streamBridge.send(
                "producePlayerPhaseStarted-in-0",
                new PlayerPhaseStartedDto(
                    currentPhase.ordinal(),
                    playerRound.getPlayerId(),
                    gameId,
                    roundId
                )
            );
        }

        return playerRound;
    }

    /**
     * Check if a player round is finished and sends an application event if it is
     * @param playerRound The player round to check
     */
    public void endPlayerRound(PlayerRound playerRound) {
        eventPublisher.publishEvent(new PlayerRoundFinishedAppEvent(this, playerRound));
    }
}
