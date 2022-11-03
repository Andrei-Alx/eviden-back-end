package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.event.RoundCreatedEventKeyValue;
import nl.fontys.atosgame.roundservice.model.CardSet;
import nl.fontys.atosgame.roundservice.model.LobbySettings;
import nl.fontys.atosgame.roundservice.model.Round;
import nl.fontys.atosgame.roundservice.model.RoundSettings;
import nl.fontys.atosgame.roundservice.repository.CardSetRepository;
import nl.fontys.atosgame.roundservice.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import reactor.util.function.Tuple2;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Service for round related operations
 * @author Eli
 */
@Service
public class RoundServiceImpl implements RoundService {

    private final RoundRepository roundRepository;

    private final CardSetService cardSetService;

    private StreamBridge streamBridge;

    public RoundServiceImpl(@Autowired RoundRepository roundRepository, @Autowired CardSetService cardSetService, @Autowired StreamBridge streamBridge) {
        this.roundRepository = roundRepository;
        this.cardSetService = cardSetService;
        this.streamBridge = streamBridge;
    }

    /**
     * Create rounds for a game
     * @param gameId The id of the game
     * @param roundSettings The settings for the rounds
     */
    @Override
    public void createRounds(UUID gameId, List<RoundSettingsDto> roundSettings) {
        for (RoundSettingsDto roundSetting : roundSettings) {
            createRound(gameId, roundSetting);
        }
    }

    /**
     * Create a round for a game
     * @param gameId The id of the game
     * @param roundSettings The settings for the round
     */
    public void createRound(UUID gameId, RoundSettingsDto roundSettings) {
        Round round = new Round(null, new ArrayList<>(), "NotStarted", null);
        // Create round settings
        RoundSettings settings = new RoundSettings(roundSettings.getId(), roundSettings.isShowPersonalOrGroupResults(), roundSettings.getNrOfLikedCards(), roundSettings.getNrOfPickedCards(), roundSettings.getShuffleMethod(), roundSettings.isShowSameCardOrder(), null);
        // Get card set
        CardSet cardSet = cardSetService.getCardSet(roundSettings.getCardSetId());
        // Add card set to round settings
        settings.setCardSet(cardSet);

        // Add round settings to round
        round.setRoundSettings(settings);

        // Save round
        round = roundRepository.save(round);

        // Produce round created event
        streamBridge.send("produceRoundCreated-in-0", new RoundCreatedEventKeyValue(gameId, round));
    }
}
