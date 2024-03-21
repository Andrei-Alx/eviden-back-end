package nl.fontys.atosgame.lobbyservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.lobbyservice.exceptions.DuplicatePlayerException;
import nl.fontys.atosgame.lobbyservice.exceptions.LobbyFullException;
import org.hibernate.annotations.Type;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lobby {

    @Id
    @GeneratedValue(generator = "UUID")
    @JsonProperty
    private UUID id;

    @JsonProperty
    @ElementCollection(fetch = FetchType.EAGER)
    private Collection<Player> players = new java.util.ArrayList<>();

    @JsonProperty
    private String lobbyCode;

    @JsonProperty
    @Embedded
    private LobbySettings lobbySettings;

    private UUID gameId;
}
