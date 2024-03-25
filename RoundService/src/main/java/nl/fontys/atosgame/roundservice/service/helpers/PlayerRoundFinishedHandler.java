package nl.fontys.atosgame.roundservice.service.helpers;

import nl.fontys.atosgame.roundservice.applicationevents.PlayerRoundFinishedAppEvent;
import nl.fontys.atosgame.roundservice.model.Game;
import nl.fontys.atosgame.roundservice.model.Round;
import nl.fontys.atosgame.roundservice.service.interfaces.GameService;
import nl.fontys.atosgame.roundservice.service.interfaces.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * ***OBSOLETE***
 * Handler that handles PlayerRoundFinishedAppEvents and tells the round service to check if the round can be finished
 * @author Eli
 */
@Service
public class PlayerRoundFinishedHandler
    implements ApplicationListener<PlayerRoundFinishedAppEvent> {

    private final RoundService roundService;
    private final GameService gameService;

    public PlayerRoundFinishedHandler(
        @Autowired RoundService roundService,
        @Autowired GameService gameService
    ) {
        this.roundService = roundService;
        this.gameService = gameService;
    }

    @Override
    public void onApplicationEvent(PlayerRoundFinishedAppEvent event) {
        Round round = roundService.getRoundByPlayerRound(event.getPlayerRound()).get();
        Game game = gameService.getGameByRoundId(round.getId()).get();
        this.roundService.checkRoundEnd(round.getId(), game.getId());
    }
}
