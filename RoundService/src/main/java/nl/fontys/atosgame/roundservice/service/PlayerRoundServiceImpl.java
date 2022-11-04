package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.model.PlayerRound;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlayerRoundServiceImpl implements PlayerRoundService {
    @Override
    public PlayerRound createPlayerRound(UUID playerId, UUID roundId) {
        // TODO
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
