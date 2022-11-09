package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lobby {
    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID id;
    @ElementCollection(fetch = javax.persistence.FetchType.EAGER)
    @JsonProperty
    private List<UUID> playerIds = new java.util.ArrayList<>();

    /**
     * Add a player to the lobby
     * @param playerId The id of the player
     */
    public void addPlayer(UUID playerId) {
        playerIds.add(playerId);
    }

    /**
     * Remove a player from the lobby
     * @param playerId The id of the player
     */
    public void removePlayer(UUID playerId) {
        playerIds.remove(playerId);
    }
}
