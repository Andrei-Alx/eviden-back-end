package nl.fontys.atosgame.roundservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Round {
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type="org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID id;
    @OneToMany
    @JsonProperty
    private List<PlayerRound> playerRounds;
    @JsonProperty
    private String status;
    @JsonProperty
    @Embedded
    private RoundSettings roundSettings;
}
