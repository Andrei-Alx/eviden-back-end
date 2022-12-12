package nl.fontys.atosgame.gameappbff.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.model.PlayerRound;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerPhaseDto {

    private PlayerRound playerRound;
    private int phaseNumber;
}
