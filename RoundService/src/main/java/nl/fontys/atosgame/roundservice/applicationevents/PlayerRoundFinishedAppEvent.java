package nl.fontys.atosgame.roundservice.applicationevents;

import nl.fontys.atosgame.roundservice.model.PlayerRound;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

public class PlayerRoundFinishedAppEvent extends ApplicationEvent {
    private PlayerRound playerRound;

    public PlayerRoundFinishedAppEvent(Object source, PlayerRound playerRound) {
        super(source);
        this.playerRound = playerRound;
    }

    public PlayerRound getPlayerRoundId() {
        return playerRound;
    }
}
