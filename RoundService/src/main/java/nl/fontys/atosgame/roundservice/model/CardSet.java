package nl.fontys.atosgame.roundservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CardSet {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID id;

    @JsonProperty
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
        name = "card_set_cards",
        joinColumns = @JoinColumn(name = "card_set_null"),
        inverseJoinColumns = @JoinColumn(name = "cards_id")
    )
    @JsonProperty
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Card> cards = new java.util.ArrayList<>();

    @JsonProperty
    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Tag> tags;
}
