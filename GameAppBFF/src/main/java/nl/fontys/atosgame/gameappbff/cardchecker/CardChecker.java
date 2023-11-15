package nl.fontys.atosgame.gameappbff.cardchecker;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;

public class CardChecker {
    @EventListener(ApplicationReadyEvent.class)
    public void checkCards()
    {

    }
}
