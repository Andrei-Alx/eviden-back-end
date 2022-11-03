package nl.fontys.atosgame.roundservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
public class RoundSettings {
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type="org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID id;

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

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "round_settings_card_set",
            joinColumns = @JoinColumn(name = "round_settings_null"),
            inverseJoinColumns = @JoinColumn(name = "card_set_id"))
    @JsonProperty
    private Set<CardSet> cardSet = new java.util.LinkedHashSet<>();
}
