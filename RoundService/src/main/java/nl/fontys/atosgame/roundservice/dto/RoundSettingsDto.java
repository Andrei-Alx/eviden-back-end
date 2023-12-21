package nl.fontys.atosgame.roundservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.enums.ShowResults;
import nl.fontys.atosgame.roundservice.enums.ShuffleMethod;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoundSettingsDto {

    @JsonProperty
    private ShowResults showPersonalOrGroupResults;

    @JsonProperty
    private int nrOfLikedCards;

    @JsonProperty
    private int nrOfSelectedCards;

    @JsonProperty
    private ShuffleMethod shuffleMethod;

    @JsonProperty
    private boolean showSameCardOrder;

    @JsonProperty
    private UUID cardSetId;
}
