package nl.fontys.atosgame.roundservice.controller;

import nl.fontys.atosgame.roundservice.event.GameCreatedEvent;
import nl.fontys.atosgame.roundservice.service.GameService;
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

    private GameService gameService;

    public GameEventConsumers(@Autowired GameService gameService) {
        this.gameService = gameService;
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
            gameService.initializeGame(event.getGameId(), event.getRoundSettings());
            return null;
        };
    }
}
