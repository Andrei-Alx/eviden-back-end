package nl.fontys.atosgame.gameappbff.event.consumed;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.event.BaseEvent;
import nl.fontys.atosgame.gameappbff.model.PlayerRoundResult;
import nl.fontys.atosgame.gameappbff.model.Result;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResultDeterminedEvent extends BaseEvent {

    private UUID id;

    private UUID roundId;

    private UUID playerId;

    private UUID gameId;

    private Result result;
}
