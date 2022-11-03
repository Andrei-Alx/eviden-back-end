package nl.fontys.atosgame.roundservice.controller;

import nl.fontys.atosgame.roundservice.event.GameCreatedEvent;
import nl.fontys.atosgame.roundservice.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

/**
 *  Collection of all event consumers for game events:
 *  - GameCreated
 *
 *  @author Eli
 */
@Controller
public class GameEventConsumers {

    private RoundService roundService;

    public GameEventConsumers(@Autowired RoundService roundService) {
        this.roundService = roundService;
    }

    /**
     * function to consume a GameCreated event
     * input topic: game-created-topic
     * output topic: -
     */
    @Bean
    public Function<Message<GameCreatedEvent>, Void> handleGameCreated() {
        return message -> {
            GameCreatedEvent event = message.getPayload();
            roundService.createRounds(event.getGameId(), event.getRoundSettings(), event.getLobbySettings());
            return null;
        };
    }
}
