package nl.fontys.atosgame.roundservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.enums.RoundStatus;
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
    private StreamBridge streamBridge;
    private RoundServiceImpl roundService;

    @BeforeEach
    void setUp() {
        cardSetService = mock(CardSetService.class);
        roundRepository = mock(RoundRepository.class);
        playerRoundService = mock(PlayerRoundService.class);
        streamBridge = mock(StreamBridge.class);
        roundService =
            spy(
                new RoundServiceImpl(
                    roundRepository,
                    cardSetService,
                    streamBridge,
                    playerRoundService
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
            "shuffle",
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
    void startRound() {
        assertTrue(false);
    }

    @Test
    void initializeRound() {
        Round round = new Round();
        UUID roundId = UUID.randomUUID();
        round.setId(roundId);
        doReturn(Optional.of(round)).when(roundRepository).findById(roundId);
        List<UUID> playerIds = new ArrayList<>() {
            {
                add(UUID.randomUUID());
                add(UUID.randomUUID());
            }
        };
        List<PlayerRound> playerRounds = new ArrayList<>() {
            {
                add(new PlayerRound());
                add(new PlayerRound());
            }
        };
        doReturn(playerRounds.get(0), playerRounds.get(1))
            .when(playerRoundService)
            .createPlayerRound(any(), any());
        when(roundRepository.save(round)).thenReturn(round);

        Round result = roundService.initializeRound(roundId, playerIds);

        assertEquals(playerRounds, result.getPlayerRounds());
        verify(roundRepository).save(round);
        verify(playerRoundService, times(2)).createPlayerRound(any(), any());
    }
}
