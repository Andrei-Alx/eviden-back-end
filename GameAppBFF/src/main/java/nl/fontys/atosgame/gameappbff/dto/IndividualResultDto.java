package nl.fontys.atosgame.gameappbff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.model.Card;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndividualResultDto {
    private String playerName;
    private String result;
    private List<Card> adviceCards;
    private List<Card> chosenCards;
}
