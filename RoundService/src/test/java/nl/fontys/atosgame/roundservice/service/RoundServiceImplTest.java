package nl.fontys.atosgame.roundservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.dto.RoundStartedDto;
import nl.fontys.atosgame.roundservice.enums.RoundStatus;
import nl.fontys.atosgame.roundservice.enums.ShuffleMethod;
import nl.fontys.atosgame.roundservice.event.produced.RoundCreatedEventKeyValue;
import nl.fontys.atosgame.roundservice.model.Card;
import nl.fontys.atosgame.roundservice.model.CardSet;
import nl.fontys.atosgame.roundservice.model.PlayerRound;
import nl.fontys.atosgame.roundservice.model.Round;
import nl.fontys.atosgame.roundservice.model.RoundSettings;
import nl.fontys.atosgame.roundservice.repository.RoundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.stream.function.StreamBridge;

class RoundServiceImplTest {

    private CardSetService cardSetService;
    private RoundRepository roundRepository;
    private PlayerRoundService playerRoundService;
    private RoundLogicService roundLogicService;
    private StreamBridge streamBridge;
    private RoundServiceImpl roundService;

    @BeforeEach
    void setUp() {
        cardSetService = mock(CardSetService.class);
        roundRepository = mock(RoundRepository.class);
        playerRoundService = mock(PlayerRoundService.class);
        roundLogicService = mock(RoundLogicService.class);
        streamBridge = mock(StreamBridge.class);
        roundService =
            spy(
                new RoundServiceImpl(
                    roundRepository,
                    cardSetService,
                    streamBridge,
                    playerRoundService,
                    roundLogicService
                )
            );
    }

    @Test
    void createRound() {
        RoundSettingsDto roundSettings = new RoundSettingsDto(
            UUID.randomUUID(),
            true,
            1,
            1,
            ShuffleMethod.FULLY_RANDOM,
            true,
            UUID.randomUUID()
        );
        RoundSettings settings = new RoundSettings(
            roundSettings.isShowPersonalOrGroupResults(),
            roundSettings.getNrOfLikedCards(),
            roundSettings.getNrOfPickedCards(),
            roundSettings.getShuffleMethod(),
            roundSettings.isShowSameCardOrder(),
            null
        );
        when(cardSetService.getCardSet(roundSettings.getCardSetId()))
            .thenReturn(Optional.of(mock(CardSet.class)));
        Round round = new Round(null, new ArrayList<>(), RoundStatus.CREATED, settings);
        when(roundRepository.save(round)).thenReturn(round);
        UUID gameId = UUID.randomUUID();

        roundService.createRound(gameId, roundSettings);

        verify(roundRepository).save(round);
        verify(streamBridge)
            .send(
                "produceRoundCreated-in-0",
                new RoundCreatedEventKeyValue(gameId, round)
            );
    }

    @Test
    void testStartRoundChangesStatus() {
        Round round = new Round(
            UUID.randomUUID(),
            new ArrayList<>(),
            RoundStatus.CREATED,
            new RoundSettings()
        );
        List<UUID> playerIds = new ArrayList<>();
        UUID gameId = UUID.randomUUID();
        // Set behavior of repository
        when(roundRepository.findById(round.getId())).thenReturn(Optional.of(round));
        when(roundRepository.save(round)).thenReturn(round);
        // Set behavior of logic service
        when(roundLogicService.initializeRound(round, playerIds)).thenReturn(round);
        when(roundLogicService.distributeCards(round)).thenReturn(round);

        // Act
        Round result = roundService.startRound(round.getId(), playerIds, gameId);

        // Assert
        assertEquals(RoundStatus.IN_PROGRESS, result.getStatus());
    }

    @Test
    public void testStartRoundSendsEvents() {
        Round round = new Round(
            UUID.randomUUID(),
            new ArrayList<>(),
            RoundStatus.CREATED,
            new RoundSettings()
        );
        List<UUID> playerIds = new ArrayList<>();
        UUID gameId = UUID.randomUUID();
        // Set behavior of repository
        when(roundRepository.findById(round.getId())).thenReturn(Optional.of(round));
        when(roundRepository.save(round)).thenReturn(round);
        // Set behavior of logic service
        when(roundLogicService.initializeRound(round, playerIds)).thenReturn(round);
        when(roundLogicService.distributeCards(round)).thenReturn(round);

        // Act
        Round result = roundService.startRound(round.getId(), playerIds, gameId);

        // Assert
        verify(streamBridge)
            .send(
                "produceRoundStarted-in-0",
                new RoundStartedDto(gameId, result.getId())
            );
    }

    @Test
    public void testStartRoundInitializesAndDistributes() {
        Round round = new Round(
            UUID.randomUUID(),
            new ArrayList<>(),
            RoundStatus.CREATED,
            new RoundSettings()
        );
        List<UUID> playerIds = new ArrayList<>();
        UUID gameId = UUID.randomUUID();
        // Set behavior of repository
        when(roundRepository.findById(round.getId())).thenReturn(Optional.of(round));
        when(roundRepository.save(round)).thenReturn(round);
        // Set behavior of logic service
        when(roundLogicService.initializeRound(round, playerIds)).thenReturn(round);
        when(roundLogicService.distributeCards(round)).thenReturn(round);

        // Act
        Round result = roundService.startRound(round.getId(), playerIds, gameId);

        // Assert
        verify(roundLogicService).initializeRound(round, playerIds);
        verify(roundLogicService).distributeCards(round);
    }
}
