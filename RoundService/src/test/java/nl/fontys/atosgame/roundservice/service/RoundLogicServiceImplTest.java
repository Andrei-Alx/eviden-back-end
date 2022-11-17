package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.enums.RoundStatus;
import nl.fontys.atosgame.roundservice.model.Round;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RoundLogicServiceImplTest {

    @Test
    void initializeRound() {
        Round round = new Round(
                UUID.randomUUID(),
                new ArrayList<>(),
                RoundStatus.CREATED,
                null
        );
        List<UUID> playerIds = new ArrayList<>(List.of(UUID.randomUUID(), UUID.randomUUID()));
        RoundLogicServiceImpl roundLogicService = new RoundLogicServiceImpl();

        Round result = roundLogicService.initializeRound(round, playerIds);

        assertEquals(2, result.getPlayerRounds().size());
        assertEquals(playerIds.get(0), result.getPlayerRounds().get(0).getPlayerId());
        assertEquals(playerIds.get(1), result.getPlayerRounds().get(1).getPlayerId());
    }
}