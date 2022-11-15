package nl.fontys.atosgame.roundservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.UUID;
import java.util.function.Function;
import nl.fontys.atosgame.roundservice.event.produced.RoundCreatedEvent;
import nl.fontys.atosgame.roundservice.event.produced.RoundCreatedEventKeyValue;
import nl.fontys.atosgame.roundservice.model.Round;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;

class RoundEventProducersTest {

    @Test
    void produceRoundCreated() {
        RoundEventProducers roundEventProducers = new RoundEventProducers();
        Function<RoundCreatedEventKeyValue, Message<RoundCreatedEvent>> function = roundEventProducers.produceRoundCreated();
        RoundCreatedEventKeyValue roundCreatedEventKeyValue = new RoundCreatedEventKeyValue(
            mock(UUID.class),
            mock(Round.class)
        );

        Message<RoundCreatedEvent> message = function.apply(roundCreatedEventKeyValue);

        assertInstanceOf(RoundCreatedEvent.class, message.getPayload());
        assertEquals("RoundService", message.getPayload().getService());
        assertEquals("RoundCreated", message.getPayload().getType());
        assertEquals(
            roundCreatedEventKeyValue.getRound(),
            message.getPayload().getRound()
        );
        assertEquals(
            roundCreatedEventKeyValue.getGameId(),
            message.getHeaders().get(KafkaHeaders.MESSAGE_KEY)
        );
    }
}
