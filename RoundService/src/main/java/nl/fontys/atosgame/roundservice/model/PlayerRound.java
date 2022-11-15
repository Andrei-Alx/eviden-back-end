package nl.fontys.atosgame.roundservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.Type;

@Entity
public class PlayerRound {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID id;

    @JsonProperty
    private UUID playerId;

    @JsonProperty
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Card> likedCards;

    @JsonProperty
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Card> pickedCards;
}
