package nl.fontys.atosgame.gameappbff.controller;

import java.util.function.Function;
import nl.fontys.atosgame.gameappbff.event.consumed.LobbyCreatedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.LobbyDeletedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.PlayerJoinedEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.PlayerQuitEvent;
import nl.fontys.atosgame.gameappbff.model.Lobby;
import nl.fontys.atosgame.gameappbff.service.GameService;
import nl.fontys.atosgame.gameappbff.service.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

/**
 * Collection of consumers for lobby related events:
 * - PlayerJoinedEvent
 * - PlayerQuitEvent
 * - LobbyDeletedEvent
 * @author Eli
 */
@Controller
public class LobbyConsumers {

    private GameService gameService;

    private LobbyService lobbyService;

    public LobbyConsumers(
            @Autowired GameService gameService,
            @Autowired LobbyService lobbyService) {
        this.gameService = gameService;
        this.lobbyService = lobbyService;
    }

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
            LobbyCreatedEvent event = message.getPayload();
            Lobby lobby = event.getLobby();
            lobby = lobbyService.createLobby(lobby);
            gameService.addLobbyToGame(event.getGameId(), lobby);
            return null;
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
