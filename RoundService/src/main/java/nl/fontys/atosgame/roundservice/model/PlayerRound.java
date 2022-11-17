package nl.fontys.atosgame.roundservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
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
    private UUID id;

    @JsonProperty
    private UUID playerId;

    @JsonProperty
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Card> likedCards = new ArrayList<>();

    @JsonProperty
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Card> pickedCards = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Card> distributedCards = new ArrayList<>();
}
