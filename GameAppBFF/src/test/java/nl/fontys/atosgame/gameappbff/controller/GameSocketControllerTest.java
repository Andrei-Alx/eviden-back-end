package nl.fontys.atosgame.gameappbff.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.UUID;
import nl.fontys.atosgame.gameappbff.dto.PlayerPhaseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.simp.SimpMessagingTemplate;

class GameSocketControllerTest {

    private SimpMessagingTemplate simpMessagingTemplate;
    private GameSocketController gameSocketController;

    @BeforeEach
    void setUp() {
        simpMessagingTemplate = mock(SimpMessagingTemplate.class);
        gameSocketController = new GameSocketController(simpMessagingTemplate);
    }

    @Test
    void playerPhase() {
        UUID gameId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        UUID playerId = UUID.randomUUID();
        int phaseNumber = 1;

        gameSocketController.playerPhase(gameId, playerId, phaseNumber);

        verify(simpMessagingTemplate)
            .convertAndSend(
                "/socket/gameapp/00000000-0000-0000-0000-000000000000/playerPhase",
                new PlayerPhaseDto(playerId, phaseNumber)
            );
    }
}
