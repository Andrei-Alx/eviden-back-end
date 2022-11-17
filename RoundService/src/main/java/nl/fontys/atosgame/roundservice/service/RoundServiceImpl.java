package nl.fontys.atosgame.roundservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.dto.RoundStartedDto;
import nl.fontys.atosgame.roundservice.enums.RoundStatus;
import nl.fontys.atosgame.roundservice.event.produced.RoundCreatedEventKeyValue;
import nl.fontys.atosgame.roundservice.model.*;
import nl.fontys.atosgame.roundservice.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
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

    private StreamBridge streamBridge;

    public RoundServiceImpl(
        @Autowired RoundRepository roundRepository,
        @Autowired CardSetService cardSetService,
        @Autowired StreamBridge streamBridge,
        @Autowired PlayerRoundService playerRoundService,
        @Autowired RoundLogicService roundLogicService
    ) {
        this.roundRepository = roundRepository;
        this.cardSetService = cardSetService;
        this.playerRoundService = playerRoundService;
        this.streamBridge = streamBridge;
        this.roundLogicService = roundLogicService;
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
        streamBridge.send("produceRoundStarted-in-0", new RoundStartedDto(gameId, roundId));

        // TODO: send distributed cards events with P-19 (and add to unit test)

        return round;
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
            roundSettings.isShowPersonalOrGroupResults(),
            roundSettings.getNrOfLikedCards(),
            roundSettings.getNrOfPickedCards(),
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
