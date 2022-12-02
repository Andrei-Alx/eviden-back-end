package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.UUID;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lobby {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID id;

    @ElementCollection(fetch = javax.persistence.FetchType.EAGER)
    @JsonProperty
    private Collection<Player> players = new java.util.ArrayList<>();

    @JsonProperty
    private String lobbyCode;

    /**
     * Add a player to the lobby
     * @param player The id of the player
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Remove a player from the lobby
     * @param playerId The id of the player
     */
    public void removePlayer(UUID playerId) {
        players.remove(playerId);
    }
}
