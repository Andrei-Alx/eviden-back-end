package nl.fontys.atosgame.roundservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.*;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.enums.RoundStatus;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Game {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID id;

    @JsonProperty
    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Round> rounds = new java.util.ArrayList<>();

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "lobby_id")
    @JsonProperty
    private Lobby lobby;

    /**
     * Get the current active round
     * @return An optional containing the current active round or an empty optional if no round is active
     */
    public Optional<Round> getCurrentRound() {
        for (Round round : rounds) {
            if (round.getStatus() == RoundStatus.IN_PROGRESS) {
                return Optional.of(round);
            }
        }
        return Optional.empty();
    }

    /**
     * Get the next round
     * @return An optional containing the next round or an empty optional if there is no more rounds
     */
    public Optional<Round> getNextRound() {
        for (Round round : rounds) {
            if (round.getStatus() == RoundStatus.CREATED) {
                return Optional.of(round);
            }
        }
        return Optional.empty();
    }
}
