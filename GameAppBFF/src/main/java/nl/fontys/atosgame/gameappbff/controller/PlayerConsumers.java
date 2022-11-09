package nl.fontys.atosgame.gameappbff.controller;

import nl.fontys.atosgame.gameappbff.event.consumed.PlayerJoinedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.PlayerQuitEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

/**
 * Collection of consumers for player related events:
 * - PlayerJoinedEvent
 * - PlayerQuitEvent
 * @author Eli
 */
@Controller
public class PlayerConsumers {

    /**
     * Id: C-26
     * Consumer for PlayerJoinedEvent
     * input topic: player-joined-topic
     * output topic: -
     */
    @Bean
    public Function<Message<PlayerJoinedEvent>, Void> handlePlayerJoined() {
        return message -> {
            // TODO: implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    /**
     * Id: C-27
     * Consumer for PlayerQuitEvent
     * input topic: player-quit-topic
     * output topic: -
     */
    @Bean
    public Function<Message<PlayerQuitEvent>, Void> handlePlayerQuit() {
        return message -> {
            // TODO: implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }
}
