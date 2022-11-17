package nl.fontys.atosgame.roundservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
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

    private StreamBridge streamBridge;

    public RoundServiceImpl(
        @Autowired RoundRepository roundRepository,
        @Autowired CardSetService cardSetService,
        @Autowired StreamBridge streamBridge,
        @Autowired PlayerRoundService playerRoundService
    ) {
        this.roundRepository = roundRepository;
        this.cardSetService = cardSetService;
        this.playerRoundService = playerRoundService;
        this.streamBridge = streamBridge;
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
     * @return The updated round
     */
    @Override
    public Round startRound(UUID roundId) {
        // TODO
        throw new UnsupportedOperationException();
    }

    /**
     * Initialize a round
     * Create all the player rounds
     * @param roundId   The id of the round
     * @param playerIds The ids of the players
     * @return The updated round
     */
    @Override
    public Round initializeRound(UUID roundId, List<UUID> playerIds) {
        Round round = roundRepository
            .findById(roundId)
            .orElseThrow(EntityNotFoundException::new);
        List<PlayerRound> playerRounds = new ArrayList<>();
        for (UUID playerId : playerIds) {
            PlayerRound playerRound = playerRoundService.createPlayerRound(
                roundId,
                playerId
            );
            playerRounds.add(playerRound);
        }
        round.setPlayerRounds(playerRounds);
        return roundRepository.save(round);
    }

    /**
     * distribute cards to all players
     *
     * @param roundId The id of the round
     * @return The updated round
     */
    @Override
    public Round distributeCards(UUID roundId) {
        // TODO
        throw new UnsupportedOperationException();
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
