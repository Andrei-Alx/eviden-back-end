package nl.fontys.atosgame.gameappbff.controller;

import nl.fontys.atosgame.gameappbff.cardseeder.CardSeeder;
import nl.fontys.atosgame.gameappbff.event.consumed.CardSetEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.function.Function;

/**
 * Collection of consumers for cardset related events:
 * - CardSetEvent
 * @author Aniek
 */
@Controller
public class CardSetConsumers {
    private CardSeeder cardSeeder;

    public CardSetConsumers(@Autowired CardSeeder cardSeeder) {
        this.cardSeeder = cardSeeder;
    }

    /**
     * Id: C-1
     * Consumer for CardSetEvent
     * input topic: card-set-topic
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
