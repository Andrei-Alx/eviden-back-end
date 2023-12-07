package nl.fontys.atosgame.cardservice.controller;

import nl.fontys.atosgame.cardservice.event.consumed.CardSetRequestEvent;
import nl.fontys.atosgame.cardservice.service.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

import java.util.function.Function;

@Controller
public class CardRequestConsumers {

    private CardSetService cardSetService;

    public CardRequestConsumers(@Autowired CardSetService cardSetservice) {
        this.cardSetService = cardSetservice;
    }


    /**
     * Id: C-1
     * Consumer for CardSetRequest and activates CardSetEventProducer
     * input topic: card-set-request-topic
     * output topic: -
     */
    @Bean
    public Function<Message<CardSetRequestEvent>, Void> handleCardSetRequest() {
        return message -> {
            cardSetService.produceCardSet();
            return null;
        };
    }
}
