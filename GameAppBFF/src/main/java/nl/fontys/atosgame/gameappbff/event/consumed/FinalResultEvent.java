package nl.fontys.atosgame.gameappbff.event.consumed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.event.BaseEvent;
import nl.fontys.atosgame.gameappbff.model.FinalResult;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinalResultEvent extends BaseEvent {

    private FinalResult finalResult;
}
