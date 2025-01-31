package nl.fontys.atosgame.roundservice.controller;

import java.util.function.Function;
import nl.fontys.atosgame.roundservice.event.GameCreatedEvent;
import nl.fontys.atosgame.roundservice.event.GameStartedEvent;
import nl.fontys.atosgame.roundservice.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

/**
 *  Collection of all event consumers for game events:
 *  - GameCreated
 *  - GameStarted
 *  @author Eli
 */
@Controller
public class GameEventConsumers {

    private final GameService gameService;

    public GameEventConsumers(@Autowired GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Id: C-15
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

    /**
     * Id: C-19
     * function to consume a GameStarted event
     * input topic: game-started-topic
     * output topic: -
     */
    @Bean
    public Function<Message<GameStartedEvent>, Void> handleGameStarted() {
        return message -> {
            GameStartedEvent event = message.getPayload();
            gameService.startGame(event.getGameId());
            return null;
        };
    }
}
