package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.enums.RoundStatus;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Round {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PlayerRound> playerRounds = new ArrayList<>();

    @JsonProperty
    private RoundStatus status;

    @JsonProperty
    @Embedded
    @LazyCollection(LazyCollectionOption.FALSE)
    private RoundSettings roundSettings;

    public void addPlayerRound(PlayerRound playerRound) {
        playerRounds.add(playerRound);
    }
}
