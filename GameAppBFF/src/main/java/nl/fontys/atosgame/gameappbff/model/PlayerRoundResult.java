package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRoundResult {

    @Id
    @GeneratedValue(generator = "UUID")
    @JsonProperty
    private UUID id;

    @JsonProperty
    private UUID roundId;

    @JsonProperty
    private UUID playerId;

    @JsonProperty
    private UUID gameId;

    @JsonProperty
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private Result result;
}
