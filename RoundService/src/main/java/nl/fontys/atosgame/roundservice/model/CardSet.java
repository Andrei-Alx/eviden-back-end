package nl.fontys.atosgame.roundservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CardSet {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID id;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "card_set_cards",
            joinColumns = @JoinColumn(name = "card_set_null"),
            inverseJoinColumns = @JoinColumn(name = "cards_id"))
    @JsonProperty
    private Collection<Card> cards = new java.util.ArrayList<>();
}
