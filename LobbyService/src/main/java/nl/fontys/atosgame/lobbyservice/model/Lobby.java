package nl.fontys.atosgame.lobbyservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.UUID;
import javax.persistence.*;

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
    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID id;

    @JsonProperty
    @ElementCollection
    private Collection<Player> players = new java.util.ArrayList<>();

    @JsonProperty
    private String lobbyCode;

    @JsonProperty
    @Embedded
    private LobbySettings lobbySettings;

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID gameId;

    public void addPlayer(Player player) throws DuplicatePlayerException, LobbyFullException {
        if (players.size() >= lobbySettings.getMaxPlayers()) {
            throw new LobbyFullException("The lobby is full.");
        }
        if (players.stream().anyMatch(p -> p.getName().equals(player.getName()))) {
            throw new DuplicatePlayerException("A player with this name already exists in this lobby.");
        }
        players.add(player);
    }
}
