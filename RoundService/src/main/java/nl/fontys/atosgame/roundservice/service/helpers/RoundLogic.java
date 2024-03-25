package nl.fontys.atosgame.roundservice.service.helpers;

import java.util.List;
import java.util.UUID;

import nl.fontys.atosgame.roundservice.model.Card;
import nl.fontys.atosgame.roundservice.model.PlayerRound;
import nl.fontys.atosgame.roundservice.model.Round;
import nl.fontys.atosgame.roundservice.service.interfaces.CardShuffler;
import nl.fontys.atosgame.roundservice.service.interfaces.RoundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for round logic related operations
 * This class is only supposed to perform logic, not interact with the database.
 *
 * @author Eli
 */
@Service
public class RoundLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoundLogic.class);
    private final CardShuffler cardShuffler;
    private final RoundService roundService;

    public RoundLogic(@Autowired CardShuffler cardShuffler, @Autowired RoundService roundService) {
        this.cardShuffler = cardShuffler;
        this.roundService = roundService;
    }

    /**
     * Initializes a round by creating player rounds for all players
     *
     * @param round     The round to initiate
     * @param playerIds The ids of the players
     * @return The initialized round
     */
    public Round initializeRound(Round round, List<UUID> playerIds) {
        LOGGER.info(String.format("initialize round event (RoundLogicServiceImpl class) => %s", round));
        for (UUID playerId : playerIds) {
            PlayerRound playerRound = new PlayerRound();
            playerRound.setPlayerId(playerId);
            playerRound.setRoundSettings(round.getRoundSettings());
            round.getPlayerRounds().add(playerRound);
        }
        return round;
    }

    /**
     * Distributes cards to all players
     *
     * @param round The round to distribute cards for
     * @return The updated round
     */
    public Round distributeCards(Round round) {
        if (round.getRoundSettings().isShowSameCardOrder()) {
            // Shuffle cards
            List<Card> deck = cardShuffler.randomShuffle(
                round.getRoundSettings().getCardSet().getCards()
            );

            // Set cards for all players
            for (PlayerRound playerRound : round.getPlayerRounds()) {
                playerRound.setDistributedCards(deck);
            }
        } else {
            for (PlayerRound playerRound : round.getPlayerRounds()) {
                // Shuffle cards
                List<Card> deck = cardShuffler.randomShuffle(
                    round.getRoundSettings().getCardSet().getCards()
                );

                // Set cards for player
                playerRound.setDistributedCards(deck);
            }
        }
        return round;
    }
}
