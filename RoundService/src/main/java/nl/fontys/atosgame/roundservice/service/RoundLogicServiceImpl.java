package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.model.PlayerRound;
import nl.fontys.atosgame.roundservice.model.Round;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoundLogicServiceImpl implements RoundLogicService {
    /**
     * Initializes a round by creating player rounds for all players
     *
     * @param round     The round to initiate
     * @param playerIds The ids of the players
     * @return The initialized round
     */
    @Override
    public Round initializeRound(Round round, List<UUID> playerIds) {
        for (UUID playerId : playerIds) {
            PlayerRound playerRound = new PlayerRound();
            playerRound.setPlayerId(playerId);
            round.addPlayerRound(playerRound);
        }
        return round;
    }

    /**
     * Distributes cards to all players
     *
     * @param round The round to distribute cards for
     * @return The updated round
     */
    @Override
    public Round distributeCards(Round round) {
        return null;
    }
}
