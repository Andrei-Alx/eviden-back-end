package nl.fontys.atosgame.roundservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.enums.ShuffleMethod;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoundSettingsDto {

    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID id;

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
