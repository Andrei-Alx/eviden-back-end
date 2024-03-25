package nl.fontys.atosgame.gameservice.controllers.produced;

import java.util.UUID;
import java.util.function.Function;
import nl.fontys.atosgame.gameservice.dto.CreateGameEventDto;
import nl.fontys.atosgame.gameservice.event.EventFactory;
import nl.fontys.atosgame.gameservice.event.produced.GameCreatedEvent;
import nl.fontys.atosgame.gameservice.event.produced.GameEndedEvent;
import nl.fontys.atosgame.gameservice.event.produced.GameStartedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;

/**
 * Collection of producers for game related events:
 * - GameCreatedEvent
 * - GameStartedEvent
 * - GameEndedEvent
 * @author Eli
 */
@Controller
public class GameProducers {

    /**
     * Id: P-11
     * Producer for GameCreatedEvent
     * input topic: -
     * output topic: game-created-topic
     */
    @Bean
    public Function<CreateGameEventDto, Message<GameCreatedEvent>> produceGameCreated() {
        return input -> {
            GameCreatedEvent event = EventFactory.createGameCreatedEvent(
                input.getGameId(),
                input.getTitle(),
                input.getCompanyType(),
                input.getRoundSettings(),
                input.getLobbySettings()
            );
            Message<GameCreatedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.KEY, input.getGameId())
                .build();
            return message;
        };
    }

    /**
     * Id: P-12
     * Producer for GameStartedEvent
     * input topic: -
     * output topic: game-started-topic
     */
    @Bean
    public Function<UUID, Message<GameStartedEvent>> produceGameStarted() {
        return input -> {
            GameStartedEvent event = EventFactory.createGameStartedEvent(input);

            return MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.KEY, input)
                .build();
        };
    }

    /**
     * Id: P-13
     * Producer for GameEndedEvent
     * input topic: -
     * output topic: game-ended-topic
     */
    @Bean
    public Function<UUID, Message<GameEndedEvent>> produceGameEnded() {
        return input -> {
            GameEndedEvent event = EventFactory.createGameEndedEvent(input);

            return MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.KEY, input)
                .build();
        };
    }
}
