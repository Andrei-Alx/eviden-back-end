package nl.fontys.atosgame.roundservice.service;

import nl.fontys.atosgame.roundservice.applicationevents.PlayerRoundFinishedAppEvent;
import nl.fontys.atosgame.roundservice.model.Round;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * Handler that handles PlayerRoundFinishedAppEvents and tells the round service to check if the round can be finished
 * @author Eli
 */
@Service
public class PlayerRoundFinishedHandler implements ApplicationListener<PlayerRoundFinishedAppEvent> {
    private final RoundService roundService;

    public PlayerRoundFinishedHandler(
           @Autowired RoundService roundService) {
        this.roundService = roundService;
    }


    @Override
    public void onApplicationEvent(PlayerRoundFinishedAppEvent event) {
        System.out.println("Player round finished");
        Round round = roundService.getRoundByPlayerRound(event.getPlayerRound()).get();
        this.roundService.checkRoundEnd(round.getId());
    }
}
