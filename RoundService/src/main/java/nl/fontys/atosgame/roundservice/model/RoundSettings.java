package nl.fontys.atosgame.roundservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.enums.ShowResults;
import nl.fontys.atosgame.roundservice.enums.ShuffleMethod;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoundSettings {

    @Id
    @GeneratedValue(generator = "UUID")
    @JsonProperty
    private UUID id;

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
    @JsonProperty
    private CardSet cardSet;
}
