package nl.fontys.atosgame.roundservice.service;

import java.util.List;
import java.util.UUID;
import nl.fontys.atosgame.roundservice.model.Card;
import nl.fontys.atosgame.roundservice.model.PlayerRound;
import nl.fontys.atosgame.roundservice.model.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for round logic related operations
 * This class is only supposed to perform logic, not interact with the database.
 *
 * @author Eli
 */
@Service
public class RoundLogicServiceImpl implements RoundLogicService {

    private CardShuffler cardShuffler;

    public RoundLogicServiceImpl(@Autowired CardShuffler cardShuffler) {
        this.cardShuffler = cardShuffler;
    }

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
            playerRound.setNrOfLikedCards(round.getRoundSettings().getNrOfLikedCards());
            playerRound.setNrOfSelectedCards(round.getRoundSettings().getNrOfSelectedCards());
            playerRound.setImportantTag("color");
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
