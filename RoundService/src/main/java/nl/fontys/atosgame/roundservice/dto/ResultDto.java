package nl.fontys.atosgame.roundservice.dto;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.enums.ResultStatus;
import nl.fontys.atosgame.roundservice.enums.ShowResults;
import nl.fontys.atosgame.roundservice.model.Card;
import nl.fontys.atosgame.roundservice.model.CardSet;
import nl.fontys.atosgame.roundservice.model.Tag;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {

    private UUID playerId;
    private ShowResults type;
    private List<String> result;
    private List<Card> chosenCards;
    private List<Card> adviceCards;
}
