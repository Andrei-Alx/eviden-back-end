package nl.fontys.atosgame.gameappbff.controller;

import nl.fontys.atosgame.gameappbff.event.consumed.LobbyCreatedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.LobbyDeletedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.PlayerJoinedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.PlayerQuitEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

/**
 * Collection of consumers for lobby related events:
 * - PlayerJoinedEvent
 * - PlayerQuitEvent
 * - LobbyDeletedEvent
 * @author Eli
 */
@Controller
public class LobbyConsumers {

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

    /**
     * Id: C-56
     * Consumer for LobbyCreatedEvent
     * input topic: lobby-created-topic
     * output topic: -
     */
    @Bean
    public Function<Message<LobbyCreatedEvent>, Void> handleLobbyCreated() {
        return message -> {
            //TODO: implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    /**
     * Id: C-41
     * Consumer for LobbyDeletedEvent
     * input topic: lobby-deleted-topic
     * output topic: -
     */
    @Bean
    public Function<Message<LobbyDeletedEvent>, Void> handleLobbyDeleted() {
        return message -> {
            //TODO: implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }
}
