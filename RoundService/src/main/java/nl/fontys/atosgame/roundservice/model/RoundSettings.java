package nl.fontys.atosgame.roundservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoundSettings {

    @JsonProperty
    private boolean showPersonalOrGroupResults;

    @JsonProperty
    private int nrOfLikedCards;

    @JsonProperty
    private int nrOfPickedCards;

    @JsonProperty
    private String shuffleMethod;

    @JsonProperty
    private boolean showSameCardOrder;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "card_set_id")
    @JsonProperty
    private CardSet cardSet;
}
