package nl.fontys.atosgame.lobbyservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.UUID;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lobby {

    /*@Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonProperty*/
    private UUID id;

    /*@ElementCollection
    @JsonProperty*/
    private Collection<Player> players;

    private String lobbyCode;

    private LobbySettings lobbySettings;
}
