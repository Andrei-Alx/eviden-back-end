package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.model.PlayerRound;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface PlayerRoundService {
    PlayerRound createPlayerRound(UUID playerId, UUID roundId);
}
