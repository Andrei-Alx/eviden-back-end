package nl.fontys.atosgame.roundservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lobby {

    @Id
    @GeneratedValue(generator = "UUID")
    @JsonProperty
    private UUID id;

    @ElementCollection(fetch = FetchType.EAGER)
    @JsonProperty
    private List<UUID> playerIds;

    @JsonProperty
    private UUID gameId;

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
