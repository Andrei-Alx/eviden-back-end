package nl.fontys.atosgame.roundservice.service;

import java.util.Map;
import nl.fontys.atosgame.roundservice.applicationevents.RoundFinishedAppEvent;
import nl.fontys.atosgame.roundservice.dto.PlayerResultDeterminedDto;
import nl.fontys.atosgame.roundservice.dto.PlayerResultIndeterminateDto;
import nl.fontys.atosgame.roundservice.dto.ResultDto;
import nl.fontys.atosgame.roundservice.enums.ResultStatus;
import nl.fontys.atosgame.roundservice.model.Game;
import nl.fontys.atosgame.roundservice.model.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * Handler that handles RoundFinishedAppEvents and tells the game service to check if the next round can be started
 * @author Eli
 */
@Service
public class RoundFinishedHandler implements ApplicationListener<RoundFinishedAppEvent> {

    private GameService gameService;
    private StreamBridge streamBridge;

    public RoundFinishedHandler(
        @Autowired GameService gameService,
        @Autowired StreamBridge streamBridge
    ) {
        this.gameService = gameService;
        this.streamBridge = streamBridge;
    }

    @Override
    public void onApplicationEvent(RoundFinishedAppEvent event) {
        System.out.println("Round finished");
        Game game = gameService.getGameByRoundId(event.getRound().getId()).get();
        this.gameService.checkForNextRound(game.getId());
        publishResults(event.getRound());
    }

    // produces an event for the determined results of the players
    //@Override
    public void publishResults(Round round) {
        round
            .getPlayerRounds()
            .forEach(playerRound -> {
                if (playerRound.hasDeterminateResult()) {
                    // playerround calculate results and produce in event
                    Map<String, Integer> tempResults = playerRound.calculateResultForAllColors();

                    ResultDto result = playerRound.getResult(tempResults);

                    PlayerResultDeterminedDto dto = new PlayerResultDeterminedDto();
                    dto.setRoundId(playerRound.getPlayerId());
                    dto.setGameId(round.getId());
                    dto.setResult(result);

                    // Send event
                    streamBridge.send("producePlayerResultDetermined-out-0", dto);
                } else {
                    // TODO: consume this event to start the playerround again

                    PlayerResultIndeterminateDto dto = new PlayerResultIndeterminateDto();
                    dto.setPlayerId(playerRound.getPlayerId());
                    dto.setRoundId(round.getId());
                    dto.setResultStatus(ResultStatus.DETERMINED);

                    // Send event
                    streamBridge.send("producePlayerResultInDeterminate-out-0", dto);
                }
            });
    }
}
