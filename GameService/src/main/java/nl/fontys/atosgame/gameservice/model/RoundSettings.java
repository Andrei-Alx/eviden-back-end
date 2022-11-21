package nl.fontys.atosgame.gameservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameservice.enums.ShuffleMethod;
import org.hibernate.annotations.Type;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoundSettings {

    @JsonProperty
    private boolean showPersonalOrGroupResults;

    @JsonProperty
    private int nrOfLikedCards;

    @JsonProperty
    private int nrOfPickedCards;

    @JsonProperty
    private ShuffleMethod shuffleMethod;

    @JsonProperty
    private boolean showSameCardOrder;

    @JsonProperty
    private UUID cardSetId;
}
