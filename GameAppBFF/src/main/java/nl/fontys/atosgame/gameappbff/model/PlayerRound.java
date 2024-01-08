package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRound {

    @Id
    @GeneratedValue(generator = "UUID")
    @JsonProperty
    private UUID id;

    @JsonProperty
    private UUID playerId;

    @JsonProperty
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Card> likedCards = new ArrayList<>();

    @JsonProperty
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Card> dislikedCards = new ArrayList<>();

    @JsonProperty
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Card> selectedCards = new ArrayList<>();

    @JsonProperty
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Card> distributedCards = new ArrayList<>();

    public void addLikedCard(Card card) {
        likedCards.add(card);
    }

    public void addDislikedCard(Card card) {
        dislikedCards.add(card);
    }

    public void addSelectedCards(List<Card> cards) {
        selectedCards.addAll(cards);
    }
}
