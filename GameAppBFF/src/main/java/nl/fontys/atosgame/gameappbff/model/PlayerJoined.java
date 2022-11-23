package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerJoined {

    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID playerId;

    @JsonProperty
    private String playerName;
}
