package nl.fontys.atosgame.roundservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;

import nl.fontys.atosgame.roundservice.applicationevents.RoundFinishedAppEvent;
import nl.fontys.atosgame.roundservice.dto.CardsDistributedDto;
import nl.fontys.atosgame.roundservice.dto.PlayerPhaseStartedDto;
import nl.fontys.atosgame.roundservice.dto.RoundEndedDto;
import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.dto.RoundStartedDto;
import nl.fontys.atosgame.roundservice.enums.RoundStatus;
import nl.fontys.atosgame.roundservice.event.produced.RoundCreatedEventKeyValue;
import nl.fontys.atosgame.roundservice.model.*;
import nl.fontys.atosgame.roundservice.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Service for round related operations
 * @author Eli
 */
@Service
public class RoundServiceImpl implements RoundService {

    private final RoundRepository roundRepository;

    private final CardSetService cardSetService;

    private final PlayerRoundService playerRoundService;

    private final RoundLogicService roundLogicService;

    private final ApplicationEventPublisher applicationEventPublisher;

    private StreamBridge streamBridge;

    public RoundServiceImpl(
        @Autowired RoundRepository roundRepository,
        @Autowired CardSetService cardSetService,
        @Autowired StreamBridge streamBridge,
        @Autowired PlayerRoundService playerRoundService,
        @Autowired RoundLogicService roundLogicService,
        @Autowired ApplicationEventPublisher eventPublisher
    ) {
        this.roundRepository = roundRepository;
        this.cardSetService = cardSetService;
        this.playerRoundService = playerRoundService;
        this.streamBridge = streamBridge;
        this.roundLogicService = roundLogicService;
        this.applicationEventPublisher = eventPublisher;
    }

    /**
     * Create rounds for a game
     * @param gameId The id of the game
     * @param roundSettings The settings for the rounds
     * @return
     */
    @Override
    public List<Round> createRounds(UUID gameId, List<RoundSettingsDto> roundSettings) {
        List<Round> rounds = new ArrayList<>();

        for (RoundSettingsDto roundSetting : roundSettings) {
            rounds.add(createRound(gameId, roundSetting));
        }

        return rounds;
    }

    /**
     * Start a round
     * Changes the status of the round to InProgress
     * Distributes the cards to the players
     * @param roundId The id of the round
     * @param playerIds the ids of the players to start the round for
     * @param gameId The id of the game
     * @return The updated round
     */
    @Override
    public Round startRound(UUID roundId, List<UUID> playerIds, UUID gameId) {
        // Get the round
        Optional<Round> roundOptional = roundRepository.findById(roundId);
        if (roundOptional.isEmpty()) {
            throw new EntityNotFoundException("Round not found");
        }
        Round round = roundOptional.get();

        // Initialize the round so that it has all the player rounds
        round = roundLogicService.initializeRound(round, playerIds);

        // Change the status of the round to InProgress and send event
        round.setStatus(RoundStatus.IN_PROGRESS);

        // Distribute the cards to the players
        round = roundLogicService.distributeCards(round);

        // Save to db
        round = roundRepository.save(round);

        // Send round started event
        streamBridge.send(
            "produceRoundStarted-in-0",
            new RoundStartedDto(gameId, roundId)
        );

        // TODO: send distributed cards events with P-19 (and add to unit test)
        for (PlayerRound playerRound : round.getPlayerRounds()) {
            streamBridge.send(
                "producePlayerCardsDistributed-in-0",
                new CardsDistributedDto(
                    roundId,
                    playerRound
                        .getDistributedCards()
                        .stream()
                        .map(Card::getId)
                        .collect(Collectors.toList()),
                    playerRound.getPlayerId(),
                    gameId
                )
            );
        }

        for (PlayerRound playerRound : round.getPlayerRounds()) {
            streamBridge.send(
                "producePlayerPhaseStarted-in-0",
                new PlayerPhaseStartedDto(0, playerRound.getPlayerId(), gameId, roundId)
            );
        }

        return round;
    }

    /**
     * End a round
     *
     * @param roundId The id of the round
     * @param gameId  The id of the game
     * @return The updated round
     */
    @Override
    public Round endRound(UUID roundId, UUID gameId) {
        // Get the round
        Round round = roundRepository
            .findById(roundId)
            .orElseThrow(EntityNotFoundException::new);

        // Change the status of the round to Finished and send event
        round.setStatus(RoundStatus.FINISHED);
        streamBridge.send("produceRoundEnded-in-0", new RoundEndedDto(gameId, roundId));

        // Save to db
        round = roundRepository.save(round);
        return round;
    }

    /**
     * Get a round by id
     *
     * @param roundId The id of the round
     * @return The round
     */
    @Override
    public Optional<Round> getRound(UUID roundId) {
        return roundRepository.findById(roundId);
    }

    /**
     * Like a card
     * Produces an event and updates the player round
     *
     * @param playerId The player to like the card for
     * @param cardId   The card to like
     * @param gameId   The game id
     * @param roundId  The round id
     * @return The updated player round
     */
    @Override
    public Round likeCard(UUID playerId, UUID cardId, UUID gameId, UUID roundId) {
        Round round = getRound(roundId).orElseThrow(EntityNotFoundException::new);
        playerRoundService.likeCard(round.getPlayerRound(playerId), cardId, gameId, roundId);
        return round;
    }

    /**
     * Dislike a card
     * Produces an event and updates the player round
     *
     * @param playerId The player to dislike the card for
     * @param cardId   The card to dislike
     * @param gameId   The game id
     * @param roundId  The round id
     * @return
     */
    @Override
    public Round dislikeCard(UUID playerId, UUID cardId, UUID gameId, UUID roundId) {
        Round round = getRound(roundId).orElseThrow(EntityNotFoundException::new);
        playerRoundService.dislikeCard(round.getPlayerRound(playerId), cardId, gameId, roundId);
        return round;
    }

    /**
     * Add cards to the selected cards of the player round
     * Produces an event and updates the round
     *
     * @param playerId The id of the player
     * @param cardIds  The ids of the cards to select
     * @param gameId   The id of the game
     * @param roundId  The id of the round
     * @return The updated round
     */
    @Override
    public Round selectCards(UUID playerId, List<UUID> cardIds, UUID gameId, UUID roundId) {
        Round round = getRound(roundId).orElseThrow(EntityNotFoundException::new);
        playerRoundService.selectCards(round.getPlayerRound(playerId), cardIds, gameId, roundId);
        return round;
    }

    /**
     * When a playerround is finished, check if round is finished and if so, launch application event
     *
     * @param roundId The id of the round
     */
    @Override
    public boolean checkRoundEnd(UUID roundId) {
        boolean roundFinished = false;
        Round round = getRound(roundId).get();
        if (round.isDone()) {
            applicationEventPublisher.publishEvent(new RoundFinishedAppEvent(this, round));
            roundFinished = true;
        }

        return roundFinished;
    }

    /**
     * Get the round that contains a playerround
     *
     * @param playerRound The the player round
     * @return The round
     */
    @Override
    public Optional<Round> getRoundByPlayerRound(PlayerRound playerRound) {
        return roundRepository.findByPlayerRoundsContaining(playerRound);
    }


    /**
     * Create a round for a game
     * @param gameId The id of the game
     * @param roundSettings The settings for the round
     */
    public Round createRound(UUID gameId, RoundSettingsDto roundSettings) {
        Round round = new Round(null, new ArrayList<>(), RoundStatus.CREATED, null);
        // Create round settings
        RoundSettings settings = new RoundSettings(
            roundSettings.getShowPersonalOrGroupResults(),
            roundSettings.getNrOfLikedCards(),
            roundSettings.getNrOfSelectedCards(),
            roundSettings.getShuffleMethod(),
            roundSettings.isShowSameCardOrder(),
            null
        );
        // Get card set
        Optional<CardSet> cardSet = cardSetService.getCardSet(
            roundSettings.getCardSetId()
        );
        if (cardSet.isEmpty()) {
            throw new IllegalArgumentException("Card set not found");
        }
        // Add round settings to round
        round.setRoundSettings(settings);
        // Save round
        round = roundRepository.save(round);
        // Add card set to round settings
        settings.setCardSet(cardSet.get());

        round = roundRepository.save(round);

        // Produce round created event
        streamBridge.send(
            "produceRoundCreated-in-0",
            new RoundCreatedEventKeyValue(gameId, round)
        );
        return round;
    }
}
