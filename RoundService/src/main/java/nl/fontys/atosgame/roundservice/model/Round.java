package nl.fontys.atosgame.roundservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.roundservice.enums.RoundStatus;
import org.hibernate.annotations.Type;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Round {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID id;

    @ElementCollection
    @JsonProperty
    private List<PlayerRound> playerRounds = new ArrayList<>();

    @JsonProperty
    private RoundStatus status;

    @JsonProperty
    @Embedded
    private RoundSettings roundSettings;
}
