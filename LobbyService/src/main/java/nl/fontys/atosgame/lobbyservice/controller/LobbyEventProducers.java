package nl.fontys.atosgame.lobbyservice.controller;

import java.util.UUID;
import java.util.function.Function;
import nl.fontys.atosgame.lobbyservice.dto.LobbyDeletedDto;
import nl.fontys.atosgame.lobbyservice.dto.LobbyJoinedDto;
import nl.fontys.atosgame.lobbyservice.dto.LobbyQuitDto;
import nl.fontys.atosgame.lobbyservice.event.EventFactory;
import nl.fontys.atosgame.lobbyservice.event.produced.LobbyCreatedEvent;
import nl.fontys.atosgame.lobbyservice.event.produced.LobbyDeletedEvent;
import nl.fontys.atosgame.lobbyservice.event.produced.PlayerJoinedEvent;
import nl.fontys.atosgame.lobbyservice.event.produced.PlayerQuitEvent;
import nl.fontys.atosgame.lobbyservice.model.Lobby;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;

/**
 * Controller for the lobby events produced by the lobby service
 * @author Aniek
 */
@Controller
public class LobbyEventProducers {

    /**
     * Id: P-7
     * function to produce a LobbyCreated event
     * input topic: -
     * output topic: lobby-created-topic
     */
    @Bean
    public Function<Lobby, Message<LobbyCreatedEvent>> produceLobbyCreated() {
        return lobby -> {
            LobbyCreatedEvent lobbyCreatedEvent = EventFactory.createLobbyCreatedEvent(
                lobby,
                lobby.getGameId()
            );
            return MessageBuilder
                .withPayload(lobbyCreatedEvent)
                .setHeader(KafkaHeaders.MESSAGE_KEY, lobby.getGameId())
                .build();
        };
    }

    /**
     * Id: P-8
     * function to produce a LobbyDeleted event
     * input topic: -
     * output topic: lobby-deleted-topic
     */
    @Bean
    public Function<LobbyDeletedDto, Message<LobbyDeletedEvent>> produceLobbyDeleted() {
        return dto -> {
            LobbyDeletedEvent lobbyDeletedEvent = EventFactory.createLobbyDeletedEvent(
                dto.getLobbyId(),
                dto.getGameId()
            );
            return MessageBuilder
                .withPayload(lobbyDeletedEvent)
                .setHeader(KafkaHeaders.MESSAGE_KEY, dto.getGameId())
                .build();
        };
    }

    /**
     * Id: P-9
     * function to produce a PlayerJoined event
     * input topic: -
     * output topic: player-joined-topic
     */
    @Bean
    public Function<LobbyJoinedDto, Message<PlayerJoinedEvent>> producePlayerJoined() {
        return keyValue -> {
            PlayerJoinedEvent playerJoinedEvent = EventFactory.createPlayerJoinedEvent(
                keyValue.getLobbyId(),
                keyValue.getRequestedPlayerId(),
                keyValue.getGameId()
            );

            return MessageBuilder
                .withPayload(playerJoinedEvent)
                .setHeader(KafkaHeaders.MESSAGE_KEY, keyValue.getGameId())
                .build();
        };
    }

    /**
     * Id: P-10
     * function to produce a PlayerQuit event
     * input topic: -
     * output topic: player-quit-topic
     */
    @Bean
    public Function<LobbyQuitDto, Message<PlayerQuitEvent>> producePlayerQuit() {
        return keyValue -> {
            PlayerQuitEvent playerQuitEvent = EventFactory.createPlayerQuitEvent(
                keyValue.getLobbyId(),
                keyValue.getPlayerId(),
                keyValue.getGameId()
            );
            return MessageBuilder
                .withPayload(playerQuitEvent)
                .setHeader(KafkaHeaders.MESSAGE_KEY, keyValue.getGameId())
                .build();
        };
    }
}
