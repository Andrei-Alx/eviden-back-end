package nl.fontys.atosgame.gameservice.applicationEvents;

import org.springframework.context.ApplicationEvent;

import java.util.UUID;

public class RoundEndedAppEvent extends ApplicationEvent {
    private UUID roundId;
    public RoundEndedAppEvent(Object source, UUID roundId) {
        super(source);
        this.roundId = roundId;
    }

    public UUID getRoundId() {
        return roundId;
    }
}
