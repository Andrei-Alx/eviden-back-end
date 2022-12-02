package nl.fontys.atosgame.roundservice.applicationevents;

import java.util.UUID;
import nl.fontys.atosgame.roundservice.model.Round;
import org.springframework.context.ApplicationEvent;

public class RoundFinishedAppEvent extends ApplicationEvent {

    private Round round;

    public RoundFinishedAppEvent(Object source, Round round) {
        super(source);
        this.round = round;
    }

    public Round getRound() {
        return round;
    }
}
