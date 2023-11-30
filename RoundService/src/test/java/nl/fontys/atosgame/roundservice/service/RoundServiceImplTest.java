package nl.fontys.atosgame.roundservice.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;
import nl.fontys.atosgame.roundservice.dto.CardsDistributedDto;
import nl.fontys.atosgame.roundservice.dto.RoundEndedDto;
import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.dto.RoundStartedDto;
import nl.fontys.atosgame.roundservice.enums.RoundStatus;
import nl.fontys.atosgame.roundservice.enums.ShowResults;
import nl.fontys.atosgame.roundservice.enums.ShuffleMethod;
import nl.fontys.atosgame.roundservice.enums.TagType;
import nl.fontys.atosgame.roundservice.event.produced.RoundCreatedEventKeyValue;
import nl.fontys.atosgame.roundservice.event.produced.RoundEndedEvent;
import nl.fontys.atosgame.roundservice.model.*;
import nl.fontys.atosgame.roundservice.repository.RoundRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.ApplicationEventPublisher;

class RoundServiceImplTest {

    private CardSetService cardSetService;
    private RoundRepository roundRepository;
    private PlayerRoundService playerRoundService;
    private RoundLogicService roundLogicService;
    private StreamBridge streamBridge;
    private ApplicationEventPublisher applicationEventPublisher;
    private RoundServiceImpl roundService;

    @BeforeEach
    void setUp() {
        cardSetService = mock(CardSetService.class);
        roundRepository = mock(RoundRepository.class);
        playerRoundService = mock(PlayerRoundService.class);
        roundLogicService = mock(RoundLogicService.class);
        streamBridge = mock(StreamBridge.class);
        applicationEventPublisher = mock(ApplicationEventPublisher.class);
        roundService =
            spy(
                new RoundServiceImpl(
                    roundRepository,
                    cardSetService,
                    streamBridge,
                    playerRoundService,
                    roundLogicService,
                    applicationEventPublisher
                )
            );
    }

    @Test
    void createRound() {
        Tag tagColor = new Tag(TagType.COLOR, "red");
        Tag tagImportantTag = new Tag(TagType.IMPORTANT_TAG, "color");

        Collection<Tag> cardTags = new ArrayList<>(List.of(tagColor));
        Collection<Tag> cardSetTags = new ArrayList<>(List.of(tagImportantTag));

        Collection<Card> cards = new ArrayList<>(
            List.of(new Card(UUID.randomUUID(), cardTags))
        );
        CardSet cardSet = new CardSet();
        cardSet.setCards(cards);
        cardSet.setTags(cardSetTags);

        RoundSettingsDto roundSettings = new RoundSettingsDto(
            ShowResults.PERSONAL,
            1,
            1,
            ShuffleMethod.FULLY_RANDOM,
            true,
            UUID.randomUUID()
        );
        RoundSettings settings = new RoundSettings(
            roundSettings.getShowPersonalOrGroupResults(),
            roundSettings.getNrOfLikedCards(),
            roundSettings.getNrOfSelectedCards(),
            roundSettings.getShuffleMethod(),
            roundSettings.isShowSameCardOrder(),
            null
        );
        when(cardSetService.getCardSet(roundSettings.getCardSetId()))
            .thenReturn(Optional.of(cardSet));
        Round round = new Round(null, new ArrayList<>(), RoundStatus.CREATED, settings);
        when(roundRepository.save(round)).thenReturn(round);

        // assert
        RoundSettings assertSettings = new RoundSettings(
            roundSettings.getShowPersonalOrGroupResults(),
            roundSettings.getNrOfLikedCards(),
            roundSettings.getNrOfSelectedCards(),
            roundSettings.getShuffleMethod(),
            roundSettings.isShowSameCardOrder(),
            cardSet
        );
        Round assertRound = new Round(
            null,
            new ArrayList<>(),
            RoundStatus.CREATED,
            assertSettings
        );
        when(roundRepository.save(assertRound)).thenReturn(assertRound);
        UUID gameId = UUID.randomUUID();

        Round result = roundService.createRound(gameId, roundSettings);

        round.setRoundSettings(assertSettings);
        verify(roundRepository).save(round);
        verify(streamBridge)
            .send(
                "produceRoundCreated-in-0",
                new RoundCreatedEventKeyValue(gameId, round)
            );
        Assert.assertSame(
            "color",
            result
                .getRoundSettings()
                .getCardSet()
                .getTags()
                .stream()
                .filter(tag -> tag.getTagKey() == TagType.IMPORTANT_TAG)
                .findFirst()
                .get()
                .getTagKey()
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
        List<UUID> playerIds = new ArrayList<>(
            List.of(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID())
        );
        List<Card> cards = new ArrayList<>(
            List.of(
                new Card(UUID.randomUUID(), null),
                new Card(UUID.randomUUID(), null),
                new Card(UUID.randomUUID(), null)
            )
        );
        List<PlayerRound> playerRounds = new ArrayList<>(
            List.of(
                new PlayerRound(null, playerIds.get(0), null, null, null, cards, null),
                new PlayerRound(null, playerIds.get(1), null, null, null, cards, null),
                new PlayerRound(null, playerIds.get(2), null, null, null, cards, null)
            )
        );
        List<UUID> cardIds = new ArrayList<>(
            List.of(cards.get(0).getId(), cards.get(1).getId(), cards.get(2).getId())
        );
        UUID gameId = UUID.randomUUID();
        Round round = new Round(
            UUID.randomUUID(),
            playerRounds,
            RoundStatus.CREATED,
            new RoundSettings()
        );
        // Set repository
        when(roundRepository.findById(round.getId())).thenReturn(Optional.of(round));
        when(roundRepository.save(any(Round.class))).thenAnswer(i -> i.getArguments()[0]);
        // Set logic service
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
        verify(streamBridge)
            .send(
                "producePlayerCardsDistributed-in-0",
                new CardsDistributedDto(result.getId(), cardIds, playerIds.get(0), gameId)
            );
        verify(streamBridge)
            .send(
                "producePlayerCardsDistributed-in-0",
                new CardsDistributedDto(result.getId(), cardIds, playerIds.get(1), gameId)
            );
        verify(streamBridge)
            .send(
                "producePlayerCardsDistributed-in-0",
                new CardsDistributedDto(result.getId(), cardIds, playerIds.get(2), gameId)
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

    @Test
    void endRound() {
        //Arrange
        Round round = new Round(UUID.randomUUID(), null, RoundStatus.IN_PROGRESS, null);
        UUID gameId = UUID.randomUUID();
        // Set behavior of repository
        when(roundRepository.findById(round.getId())).thenReturn(Optional.of(round));
        when(roundRepository.save(round)).thenReturn(round);
        //doNothing().when(roundService).publishResults(any(), any());

        // Act
        Round result = roundService.endRound(round.getId(), gameId);

        // Assert
        assertEquals(RoundStatus.FINISHED, result.getStatus());
        verify(streamBridge)
            .send("produceRoundEnded-in-0", new RoundEndedDto(gameId, result.getId()));
        verify(roundRepository).save(result);
        //verify(roundService).publishResults(round.getId(), gameId);
    }

    @Test
    void getRound() {
        Round round = mock(Round.class);
        when(roundRepository.findById(round.getId())).thenReturn(Optional.of(round));

        Optional<Round> result = roundService.getRound(round.getId());

        assertEquals(Optional.of(round), result);
    }

    @Test
    void likeCard() {
        UUID roundId = UUID.randomUUID();
        UUID playerId = UUID.randomUUID();
        UUID gameId = UUID.randomUUID();
        UUID cardId = UUID.randomUUID();
        PlayerRound playerRound = new PlayerRound();
        playerRound.setPlayerId(playerId);
        Round round = new Round(
            roundId,
            List.of(playerRound),
            RoundStatus.IN_PROGRESS,
            null
        );
        doReturn(Optional.of(round)).when(roundService).getRound(roundId);

        Round result = roundService.likeCard(playerId, cardId, gameId, roundId);

        verify(playerRoundService).likeCard(playerRound, cardId, gameId, roundId);
    }

    @Test
    void dislikeCard() {
        UUID roundId = UUID.randomUUID();
        UUID playerId = UUID.randomUUID();
        UUID gameId = UUID.randomUUID();
        UUID cardId = UUID.randomUUID();
        PlayerRound playerRound = new PlayerRound();
        playerRound.setPlayerId(playerId);
        Round round = new Round(
            roundId,
            List.of(playerRound),
            RoundStatus.IN_PROGRESS,
            null
        );
        doReturn(Optional.of(round)).when(roundService).getRound(roundId);

        Round result = roundService.dislikeCard(playerId, cardId, gameId, roundId);

        verify(playerRoundService).dislikeCard(playerRound, cardId, gameId, roundId);
    }

    @Test
    void selectCards() {
        UUID roundId = UUID.randomUUID();
        UUID playerId = UUID.randomUUID();
        UUID gameId = UUID.randomUUID();
        UUID cardId = UUID.randomUUID();
        PlayerRound playerRound = new PlayerRound();
        playerRound.setPlayerId(playerId);
        Round round = new Round(
            roundId,
            List.of(playerRound),
            RoundStatus.IN_PROGRESS,
            null
        );
        doReturn(Optional.of(round)).when(roundService).getRound(roundId);

        Round result = roundService.selectCards(
            playerId,
            List.of(cardId),
            gameId,
            roundId
        );

        verify(playerRoundService)
            .selectCards(playerRound, List.of(cardId), gameId, roundId);
    }

    @Test
    void getRoundByPlayerRound() {
        Round round = new Round();
        PlayerRound playerRound = new PlayerRound();
        when(roundRepository.findByPlayerRoundsContaining(playerRound))
            .thenReturn(Optional.of(round));

        Optional<Round> result = roundService.getRoundByPlayerRound(playerRound);

        assertEquals(Optional.of(round), result);
    }
}
