package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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

    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonProperty
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Card> likedCards = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonProperty
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Card> dislikedCards = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonProperty
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Card> selectedCards = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonProperty
    @ManyToMany(cascade = { CascadeType.PERSIST })
    private List<Card> distributedCards = new ArrayList<>();

    public void addLikedCard(Card card) {
        likedCards.add(card);
    }

    public void addDislikedCard(Card card) {
        dislikedCards.add(card);
    }

    public void addSelectedCards(List cards) {
        selectedCards.addAll(cards);
    }
}
