package nl.fontys.atosgame.gameappbff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.model.Card;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupResultDto {
    private List<IndividualResultDto> individualResults;
    private String groupResult;
    private String groupInformation;
    private List<Card> groupAdviceCards;
}
