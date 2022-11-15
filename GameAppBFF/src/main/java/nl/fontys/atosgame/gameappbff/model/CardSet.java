package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CardSet {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String type;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "card_set_cards",
        joinColumns = @JoinColumn(name = "card_set_null"),
        inverseJoinColumns = @JoinColumn(name = "cards_id")
    )
    @JsonProperty
    private Collection<Card> cards = new java.util.ArrayList<>();
}
