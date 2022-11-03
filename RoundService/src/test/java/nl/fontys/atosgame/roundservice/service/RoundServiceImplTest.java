package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.event.RoundCreatedEventKeyValue;
import nl.fontys.atosgame.roundservice.model.Round;
import nl.fontys.atosgame.roundservice.model.RoundSettings;
import nl.fontys.atosgame.roundservice.repository.RoundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.stream.function.StreamBridge;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.*;

class RoundServiceImplTest {

    private CardSetService cardSetService;
    private RoundRepository roundRepository;
    private StreamBridge streamBridge;
    private RoundServiceImpl roundService;

    @BeforeEach
    void setUp() {
        cardSetService = mock(CardSetService.class);
        roundRepository = mock(RoundRepository.class);
        streamBridge = mock(StreamBridge.class);
        roundService = spy(new RoundServiceImpl(roundRepository, cardSetService, streamBridge));
    }

    @Test
    void createRound() {
        RoundSettingsDto roundSettings = new RoundSettingsDto(UUID.randomUUID(), true, 1, 1, "shuffle", true, UUID.randomUUID());
        RoundSettings settings = new RoundSettings(roundSettings.isShowPersonalOrGroupResults(), roundSettings.getNrOfLikedCards(), roundSettings.getNrOfPickedCards(), roundSettings.getShuffleMethod(), roundSettings.isShowSameCardOrder(), null);
        when(cardSetService.getCardSet(roundSettings.getCardSetId())).thenReturn(null);
        Round round = new Round(null, new ArrayList<>(), "NotStarted", settings);
        when(roundRepository.save(round)).thenReturn(round);
        UUID gameId = UUID.randomUUID();

        roundService.createRound(gameId, roundSettings);

        verify(roundRepository).save(round);
        verify(streamBridge).send("produceRoundCreated-in-0", new RoundCreatedEventKeyValue(gameId, round));
    }
}