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

    @Bean
    public Function<Message<CardSetRequestEvent>, Void> handleCardSetRequest() {
        return message -> {
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            cardSetService.produceCardSet();
                        }
                    },
                    5000
            );
            return null;
        };
    }
}
