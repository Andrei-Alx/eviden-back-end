package nl.fontys.atosgame.roundservice.event;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.enums.RoundStatus;
import nl.fontys.atosgame.roundservice.event.produced.PlayerCardsDistributed;
import nl.fontys.atosgame.roundservice.event.produced.PlayerPhaseEndedEvent;
import nl.fontys.atosgame.roundservice.event.produced.PlayerPhaseStartedEvent;
import nl.fontys.atosgame.roundservice.event.produced.RoundCreatedEvent;
import nl.fontys.atosgame.roundservice.event.produced.RoundEndedEvent;
import nl.fontys.atosgame.roundservice.event.produced.RoundStartedEvent;
import nl.fontys.atosgame.roundservice.model.Round;
import nl.fontys.atosgame.roundservice.model.RoundSettings;
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
}
