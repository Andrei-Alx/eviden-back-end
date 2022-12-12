package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.applicationevents.RoundFinishedAppEvent;
import nl.fontys.atosgame.roundservice.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * Handler that handles RoundFinishedAppEvents and tells the game service to check if the next round can be started
 * @author Eli
 */
@Service
public class RoundFinishedHandler implements ApplicationListener<RoundFinishedAppEvent> {

    private GameService gameService;

    public RoundFinishedHandler(@Autowired GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void onApplicationEvent(RoundFinishedAppEvent event) {
        System.out.println("Round finished");
        Game game = gameService.getGameByRoundId(event.getRound().getId()).get();
        this.gameService.checkForNextRound(game.getId());
    }
}
