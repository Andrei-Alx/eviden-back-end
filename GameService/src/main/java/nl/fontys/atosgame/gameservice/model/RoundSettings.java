package nl.fontys.atosgame.gameservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.enums.ShowResults;
import nl.fontys.atosgame.gameservice.enums.ShuffleMethod;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoundSettings {

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
