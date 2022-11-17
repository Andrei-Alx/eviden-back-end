package nl.fontys.atosgame.roundservice.service;

import java.util.List;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.model.Round;

/**
 * Service for round logic operations.
 * This class is only supposed to perform logic, not interact with the database.
 *
 * @author Eli
 */
public interface RoundLogicService {
    /**
     * Initializes a round by creating player rounds for all players
     * @param round The round to initiate
     * @param playerIds The ids of the players
     * @return The initialized round
     */
    Round initializeRound(Round round, List<UUID> playerIds);

    /**
     * Distributes cards to all players
     * @param round The round to distribute cards for
     * @return The updated round
     */
    Round distributeCards(Round round);
}
