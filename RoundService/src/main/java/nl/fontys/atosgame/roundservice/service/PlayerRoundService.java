package nl.fontys.atosgame.roundservice.service;

import java.util.UUID;
import nl.fontys.atosgame.roundservice.model.PlayerRound;
import org.springframework.stereotype.Service;

public interface PlayerRoundService {
    PlayerRound createPlayerRound(UUID playerId, UUID roundId);
}
