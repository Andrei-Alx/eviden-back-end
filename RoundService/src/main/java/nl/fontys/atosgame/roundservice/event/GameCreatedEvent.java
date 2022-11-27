package nl.fontys.atosgame.roundservice.event;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.dto.RoundSettingsDto;
import nl.fontys.atosgame.roundservice.model.LobbySettings;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameCreatedEvent extends BaseEvent {

    private UUID gameId;
    private List<RoundSettingsDto> roundSettings;
}
