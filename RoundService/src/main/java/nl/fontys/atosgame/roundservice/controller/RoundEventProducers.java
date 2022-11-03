package nl.fontys.atosgame.roundservice.controller;

import nl.fontys.atosgame.roundservice.event.EventFactory;
import nl.fontys.atosgame.roundservice.event.RoundCreatedEvent;
import nl.fontys.atosgame.roundservice.event.RoundCreatedEventKeyValue;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

/**
 * Collection of all event producers for round events:
 * - RoundCreated
 * @author Eli
 */
@Controller
public class RoundEventProducers {

    /**
     * function to produce a RoundCreated event
     * input topic: -
     * output topic: round-created-topic
     */
    @Bean
    public Function<RoundCreatedEventKeyValue, Message<RoundCreatedEvent>> produceRoundCreated() {
        return (keyValue) -> {
            RoundCreatedEvent event = EventFactory.createRoundCreatedEvent("RoundService", keyValue.getRound());
            return MessageBuilder.withPayload(event)
                    .setHeader(KafkaHeaders.MESSAGE_KEY, keyValue.getGameId())
                    .build();
        };
    }

}
