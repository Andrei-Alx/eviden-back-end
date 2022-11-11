package nl.fontys.atosgame.lobbyservice.controller;

import nl.fontys.atosgame.lobbyservice.event.produced.LobbyCreatedEvent;
import nl.fontys.atosgame.lobbyservice.event.produced.LobbyDeletedEvent;
import nl.fontys.atosgame.lobbyservice.event.produced.PlayerJoinedEvent;
import nl.fontys.atosgame.lobbyservice.event.produced.PlayerQuitEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

/**
 * Controller for the lobby events produced by the lobby service
 * @author Aniek
 */
@Controller
public class LobbyEventProducers {

    /**
     * function to produce a LobbyCreated event
     * input topic: -
     * output topic: lobby-created-topic
     */
    @Bean
    public Function<?, Message<LobbyCreatedEvent>> produceLobbyCreated() {
        return (keyValue) -> {
            // TODO implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    /**
     * function to produce a LobbyDeleted event
     * input topic: -
     * output topic: lobby-deleted-topic
     */
    @Bean
    public Function<?, Message<LobbyDeletedEvent>> produceLobbyDeleted() {
        return (keyValue) -> {
            // TODO implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    /**
     * function to produce a PlayerJoined event
     * input topic: -
     * output topic: player-joined-topic
     */
    @Bean
    public Function<?, Message<PlayerJoinedEvent>> producePlayerJoined() {
        return (keyValue) -> {
            // TODO implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }

    /**
     * function to produce a PlayerQuit event
     * input topic: -
     * output topic: player-quit-topic
     */
    @Bean
    public Function<?, Message<PlayerQuitEvent>> producePlayerQuit() {
        return (keyValue) -> {
            // TODO implement
            throw new UnsupportedOperationException("Not implemented yet");
        };
    }
}
