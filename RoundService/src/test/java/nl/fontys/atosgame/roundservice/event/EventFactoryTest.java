package nl.fontys.atosgame.roundservice.event;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import nl.fontys.atosgame.roundservice.dto.ResultDto;
import nl.fontys.atosgame.roundservice.enums.ResultStatus;
import nl.fontys.atosgame.roundservice.enums.RoundStatus;
import nl.fontys.atosgame.roundservice.event.produced.*;
import nl.fontys.atosgame.roundservice.model.Round;
import nl.fontys.atosgame.roundservice.model.RoundSettings;
import nl.fontys.atosgame.roundservice.model.Tag;
import org.junit.jupiter.api.Test;

class EventFactoryTest {

    @Test
    void createRoundCreatedEvent() {
        Round round = new Round(
            UUID.randomUUID(),
            new ArrayList<>(),
            RoundStatus.CREATED,
            new RoundSettings()
        );
        UUID gameId = UUID.randomUUID();

        RoundCreatedEvent event = EventFactory.createRoundCreatedEvent(
            "RoundService",
            round,
            gameId
        );

        assertEquals("RoundCreated", event.getType());
        assertEquals("RoundService", event.getService());
        assertEquals(round, event.getRound());
        assertEquals(gameId, event.getGameId());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getId());
    }

    @Test
    void createPlayerCardsDistributedEvent() {
        UUID roundId = UUID.randomUUID();
        UUID playerId = UUID.randomUUID();
        UUID gameId = UUID.randomUUID();
        ArrayList<UUID> cardIds = new ArrayList<>();

        PlayerCardsDistributed event = EventFactory.createPlayerCardsDistributedEvent(
            roundId,
            playerId,
            gameId,
            cardIds
        );

        assertEquals("PlayerCardsDistributed", event.getType());
        assertEquals("RoundService", event.getService());
        assertEquals(roundId, event.getRoundId());
        assertEquals(playerId, event.getPlayerId());
        assertEquals(gameId, event.getGameId());
        assertEquals(cardIds, event.getCardIds());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getId());
    }

    @Test
    void createRoundStartedEvent() {
        UUID roundId = UUID.randomUUID();
        UUID gameId = UUID.randomUUID();

        RoundStartedEvent event = EventFactory.createRoundStartedEvent(roundId, gameId);

        assertEquals("RoundStarted", event.getType());
        assertEquals("RoundService", event.getService());
        assertEquals(roundId, event.getRoundId());
        assertEquals(gameId, event.getGameId());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getId());
    }

    @Test
    void createPlayerPhaseStartedEvent() {
        int phaseNumber = 1;
        UUID roundId = UUID.randomUUID();
        UUID playerId = UUID.randomUUID();
        UUID gameId = UUID.randomUUID();

        PlayerPhaseStartedEvent event = EventFactory.createPlayerPhaseStartedEvent(
            phaseNumber,
            roundId,
            gameId,
            playerId
        );

        assertEquals("PlayerPhaseStarted", event.getType());
        assertEquals("RoundService", event.getService());
        assertEquals(roundId, event.getRoundId());
        assertEquals(playerId, event.getPlayerId());
        assertEquals(gameId, event.getGameId());
        assertEquals(phaseNumber, event.getPhaseNumber());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getId());
    }

    @Test
    void createRoundEndedEvent() {
        UUID roundId = UUID.randomUUID();
        UUID gameId = UUID.randomUUID();

        RoundEndedEvent event = EventFactory.createRoundEndedEvent(roundId, gameId);

        assertEquals("RoundEnded", event.getType());
        assertEquals("RoundService", event.getService());
        assertEquals(roundId, event.getRoundId());
        assertEquals(gameId, event.getGameId());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getId());
    }

    @Test
    void createPlayerPhaseEndedEvent() {
        int phaseNumber = 1;
        UUID roundId = UUID.randomUUID();
        UUID playerId = UUID.randomUUID();
        UUID gameId = UUID.randomUUID();

        PlayerPhaseEndedEvent event = EventFactory.createPlayerPhaseEndedEvent(
            phaseNumber,
            roundId,
            gameId,
            playerId
        );

        assertEquals("PlayerPhaseEnded", event.getType());
        assertEquals("RoundService", event.getService());
        assertEquals(roundId, event.getRoundId());
        assertEquals(playerId, event.getPlayerId());
        assertEquals(gameId, event.getGameId());
        assertEquals(phaseNumber, event.getPhaseNumber());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getId());
    }

    @Test
    void createPlayerLikedCardEvent() {
        UUID roundId = UUID.randomUUID();
        UUID playerId = UUID.randomUUID();
        UUID gameId = UUID.randomUUID();
        UUID cardId = UUID.randomUUID();

        PlayerLikedCard event = EventFactory.createPlayerLikedCardEvent(
            roundId,
            gameId,
            playerId,
            cardId
        );

        assertEquals("PlayerLikedCard", event.getType());
        assertEquals("RoundService", event.getService());
        assertEquals(roundId, event.getRoundId());
        assertEquals(playerId, event.getPlayerId());
        assertEquals(gameId, event.getGameId());
        assertEquals(cardId, event.getCardId());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getId());
    }

    @Test
    void createPlayerDislikedCardEvent() {
        UUID roundId = UUID.randomUUID();
        UUID playerId = UUID.randomUUID();
        UUID gameId = UUID.randomUUID();
        UUID cardId = UUID.randomUUID();

        PlayerDislikedCard event = EventFactory.createPlayerDislikedCardEvent(
            roundId,
            gameId,
            playerId,
            cardId
        );

        assertEquals("PlayerDislikedCard", event.getType());
        assertEquals("RoundService", event.getService());
        assertEquals(roundId, event.getRoundId());
        assertEquals(playerId, event.getPlayerId());
        assertEquals(gameId, event.getGameId());
        assertEquals(cardId, event.getCardId());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getId());
    }

    @Test
    void createPlayerSelectedCardsEvent() {
        UUID roundId = UUID.randomUUID();
        UUID playerId = UUID.randomUUID();
        UUID gameId = UUID.randomUUID();
        ArrayList<UUID> cardIds = new ArrayList<>();

        PlayerSelectedCards event = EventFactory.createPlayerSelectedCardsEvent(
            roundId,
            gameId,
            playerId,
            cardIds
        );

        assertEquals("PlayerSelectedCards", event.getType());
        assertEquals("RoundService", event.getService());
        assertEquals(roundId, event.getRoundId());
        assertEquals(playerId, event.getPlayerId());
        assertEquals(gameId, event.getGameId());
        assertEquals(cardIds, event.getCardIds());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getId());
    }

    @Test
    void createPlayerResultDeterminedEvent(){
        UUID roundId = UUID.randomUUID();
        UUID playerId = UUID.randomUUID();
        UUID gameId = UUID.randomUUID();
        ResultDto result = new ResultDto();
        result.setPlayerId(playerId);
        result.setStatus(ResultStatus.DETERMINED);
        result.setTags(List.of(new Tag("tag1", "testtag1"), new Tag("tag2", "testtag2")));

        PlayerResultDeterminedEvent event = EventFactory.createPlayerResultDeterminedEvent(
            roundId,
            gameId,
            playerId,
            result
        );

        assertEquals("PlayerResultDetermined", event.getType());
        assertEquals("RoundService", event.getService());
        assertEquals(roundId, event.getRoundId());
        assertEquals(playerId, event.getPlayerId());
        assertEquals(gameId, event.getGameId());
        assertEquals(result, event.getResult());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getId());
    }

    @Test
    void createPlayerResultIndeterminateEvent(){
        UUID roundId = UUID.randomUUID();
        UUID playerId = UUID.randomUUID();
        UUID gameId = UUID.randomUUID();
        ResultDto result = new ResultDto();
        result.setPlayerId(playerId);
        result.setStatus(ResultStatus.INDETERMINATE);

        PlayerResultIndeterminateEvent event = EventFactory.createPlayerResultIndeterminedEvent(
            roundId,
            gameId,
            playerId,
            ResultStatus.INDETERMINATE
        );

        assertEquals("PlayerResultIndeterminate", event.getType());
        assertEquals("RoundService", event.getService());
        assertEquals(roundId, event.getRoundId());
        assertEquals(playerId, event.getPlayerId());
        assertEquals(gameId, event.getGameId());
        assertEquals(ResultStatus.INDETERMINATE, event.getResultStatus());
        assertNotNull(event.getTimestamp());
        assertNotNull(event.getId());
    }
}
