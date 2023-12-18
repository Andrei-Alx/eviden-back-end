package nl.fontys.atosgame.gameservice.controllers;

import java.io.IOException;
import java.util.function.Function;

import nl.fontys.atosgame.gameservice.cardseeder.CardSeeder;
import nl.fontys.atosgame.gameservice.event.consumed.CardSetEvent;
import nl.fontys.atosgame.gameservice.service.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

/**
 * Collection of consumers for cardset related events:
 * - CardSetCreated
 * - CardSetUpdated
 * - CardSetDeleted
 * @author Eli, Aniek
 */
@Controller
public class CardSetConsumers {

    private CardSeeder cardSeeder;

    public CardSetConsumers(@Autowired CardSeeder cardSeeder) {
        this.cardSeeder = cardSeeder;
    }

    /**
     * Id: C-5
     * Consumer for CardSetCreatedEvent
     * input topic: card-set-created-topic
     * output topic: -
     */
    @Bean
    public Function<Message<CardSetEvent>, Void> handleCardSet() {
        return message -> {
            CardSetEvent event = message.getPayload();
            try {
                cardSeeder.handleCardSet(event.getCardSets());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        };
    }
}
