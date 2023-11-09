package nl.fontys.atosgame.gameappbff.controller;

import java.util.function.Function;
import javax.transaction.Transactional;
import nl.fontys.atosgame.gameappbff.event.consumed.FinalResultEvent;
import nl.fontys.atosgame.gameappbff.event.consumed.PlayerResultDeterminedEvent;
import nl.fontys.atosgame.gameappbff.model.PlayerRoundResult;
import nl.fontys.atosgame.gameappbff.service.ResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Controller;

/**
 * Collection of consumers for result related events:
 * - PlayerResultDetermined
 * - FinalResults
 */

@Controller
public class ResultConsumers {

    private ResultService resultService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ResultConsumers.class);
    private ResultConsumers(@Autowired ResultService resultService) {
        this.resultService = resultService;
    }

    /**
     * C-38
     * Consumer for PlayerResultDetermined
     * input topic: player-result-determined-topic
     * output topic: -
     */
    @Bean
    public Function<Message<PlayerResultDeterminedEvent>, Void> handlePlayerResultDetermined() {
        System.out.println("handlePlayerResultDetermined");
        return message -> {
            LOGGER.info(String.format("result determined => %s", message));
            PlayerResultDeterminedEvent event = message.getPayload();
            resultService.handlePlayerResultDetermined(
                new PlayerRoundResult(
                    event.getId(),
                    event.getRoundId(),
                    event.getPlayerId(),
                    event.getGameId(),
                    event.getResult()
                )
            );
            return null;
        };
    }

    /**
     * C-39
     * Consumer for FinalResults
     * input topic: final-results-topic
     * output topic: -
     */
    @Bean
    public Function<Message<FinalResultEvent>, Void> handleFinalResultDetermined() {
        return message -> {
            FinalResultEvent event = message.getPayload();
            resultService.handleFinalResultDetermined(event.getFinalResult());
            return null;
        };
    }
}
