package nl.fontys.atosgame.gameappbff.controller;

import java.util.function.Function;
import nl.fontys.atosgame.gameappbff.event.consumed.GameCreatedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.GameEndedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.GameStartedEvent;
import nl.fontys.atosgame.gameappbff.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

/**
 * Collection of consumers for game related events:
 * - GameCreatedEvent
 * - GameStartedEvent
 * - GameEndedEvent
 * @author Eli, Aniek
 */
@Controller
public class GameConsumers {

    private GameService gameService;

    public GameConsumers(@Autowired GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Id: C-26
     * Consumer for GameCreatedEvent
     * input topic: game-created-topic
     * output topic: -
     */
    @Bean
    public Function<Message<GameCreatedEvent>, Void> handleGameCreated() {
        return message -> {
            GameCreatedEvent event = message.getPayload();
            gameService.handleGameCreated(event.getGameId(), event.getTitle(), event.getCompanyType());
            return null;
        };
    }

    /**
     * Id: C-29
     * Consumer for GameStartedEvent
     * input topic: game-started-topic
     * output topic: -
     */
    @Bean
    public Function<Message<GameStartedEvent>, Void> handleGameStarted() {
        return message -> {
            GameStartedEvent event = message.getPayload();
            gameService.handleGameStarted(event.getGameId());
            return null;
        };
    }

    /**
     * Id: C-40
     * Consumer for GameEndedEvent
     * input topic: game-ended-topic
     * output topic: -
     */
    @Bean
    public Function<Message<GameEndedEvent>, Void> handleGameEnded() {
        return message -> {
            GameEndedEvent event = message.getPayload();
            gameService.handleGameEnded(event.getGameId());
            return null;
        };
    }
}
