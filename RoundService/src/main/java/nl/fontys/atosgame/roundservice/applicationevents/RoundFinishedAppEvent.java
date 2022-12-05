package nl.fontys.atosgame.roundservice.applicationevents;

import nl.fontys.atosgame.roundservice.dto.PlayerResultDeterminedDto;
import nl.fontys.atosgame.roundservice.dto.PlayerResultIndeterminedDto;
import nl.fontys.atosgame.roundservice.dto.ResultDto;
import nl.fontys.atosgame.roundservice.model.Round;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.ApplicationEvent;

import java.util.Map;

public class RoundFinishedAppEvent extends ApplicationEvent {
    private Round round;
    private StreamBridge streamBridge;

    public RoundFinishedAppEvent(Object source, Round round) {
        super(source);
        this.round = round;
        publishResults();
    }

    public Round getRound() {
        return round;
    }

    // produces an event for the determined results of the players
    public void publishResults() {
        round.getPlayerRounds().forEach(playerRound -> {
            if(playerRound.hasDeterminateResult()){
                // playerround calculate results and produce in event
                Map<String, Integer> tempResults = playerRound.calculateResultForAllColors();

                ResultDto result = playerRound.getResult(tempResults);

                PlayerResultDeterminedDto dto = new PlayerResultDeterminedDto();
                dto.setRoundId(playerRound.getPlayerId());
                dto.setGameId(round.getId());
                dto.setResult(result);

                // Send event
                streamBridge.send("producePlayerResultDetermined-out-0", dto);
            }
            else{
                // TODO: consume this event to start the playerround again

                PlayerResultIndeterminedDto dto = new PlayerResultIndeterminedDto();
                dto.setPlayerId(playerRound.getPlayerId());
                dto.setRoundId(round.getId());
                dto.setResultStatus("Result is indetermined");

                // Send event
                streamBridge.send("producePlayerResultInDetermined-out-0", dto);
            }
        });
    }
}
