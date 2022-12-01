package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.enums.ShowResults;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    private String shuffleMethod;

    @JsonProperty
    private boolean showSameCardOrder;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "card_set_id")
    @JsonProperty
    @LazyCollection(LazyCollectionOption.FALSE)
    private CardSet cardSet;
}
