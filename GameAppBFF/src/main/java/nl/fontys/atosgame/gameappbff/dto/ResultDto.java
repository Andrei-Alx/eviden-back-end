package nl.fontys.atosgame.gameappbff.dto;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.enums.ShowResults;
import nl.fontys.atosgame.gameappbff.model.Card;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {

    private PlayerDto player;
    private ShowResults type;
    private Collection<String> result;
    private Collection<ResultCardDto> chosenCards;
    private Collection<ResultCardDto> adviceCards;
}
