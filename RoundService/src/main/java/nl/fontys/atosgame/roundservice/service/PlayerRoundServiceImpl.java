package nl.fontys.atosgame.roundservice.service;

import java.util.UUID;
import nl.fontys.atosgame.roundservice.model.PlayerRound;
import org.springframework.stereotype.Service;

@Service
public class PlayerRoundServiceImpl implements PlayerRoundService {

    @Override
    public PlayerRound createPlayerRound(UUID playerId, UUID roundId) {
        // TODO
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
