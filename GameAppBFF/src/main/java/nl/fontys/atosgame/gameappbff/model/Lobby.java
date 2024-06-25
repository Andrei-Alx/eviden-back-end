package nl.fontys.atosgame.gameappbff.model;

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

    @OneToMany(fetch = FetchType.EAGER)
    @JsonProperty
    private List<Player> players;

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
