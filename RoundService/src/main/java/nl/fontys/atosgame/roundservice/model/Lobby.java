package nl.fontys.atosgame.roundservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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

    @ElementCollection
    @Type(type = "org.hibernate.type.UUIDCharType")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonProperty
    private List<UUID> playerIds = new java.util.ArrayList<>();

    @Type(type = "org.hibernate.type.UUIDCharType")
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
