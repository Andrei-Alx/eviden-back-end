package nl.fontys.atosgame.roundservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.enums.ShowResults;
import nl.fontys.atosgame.roundservice.enums.ShuffleMethod;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "card_set_id")
    @JsonProperty
    private CardSet cardSet;
}
