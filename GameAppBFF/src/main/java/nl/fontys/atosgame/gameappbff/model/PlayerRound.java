package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRound {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID id;

    @JsonProperty
    private UUID playerId;

    @JsonProperty
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Card> likedCards;

    @JsonProperty
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Card> pickedCards;

    @JsonProperty
    @ManyToMany(cascade = { CascadeType.PERSIST })
    private List<Card> distributedCards;
}
