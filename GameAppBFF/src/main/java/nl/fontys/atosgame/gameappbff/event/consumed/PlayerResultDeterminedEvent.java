package nl.fontys.atosgame.gameappbff.event.consumed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.event.BaseEvent;
import nl.fontys.atosgame.gameappbff.model.PlayerRoundResult;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResultDeterminedEvent extends BaseEvent {

    private PlayerRoundResult playerRoundResult;
}
