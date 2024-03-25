package nl.fontys.atosgame.roundservice.controller.produced;

import java.io.IOException;
import java.util.function.Function;

import nl.fontys.atosgame.roundservice.cardseeder.CardSeeder;
import nl.fontys.atosgame.roundservice.event.CardSetEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

@Controller
public class CardSetEventConsumers {
    private final CardSeeder cardSeeder;

    public CardSetEventConsumers(@Autowired CardSeeder cardSeeder) {
        this.cardSeeder = cardSeeder;
    }

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
