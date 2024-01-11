package nl.fontys.atosgame.lobbyservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.lobbyservice.model.Player;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LobbyJoinedDto {

    @JsonProperty
    private UUID lobbyId;

    @JsonProperty
    private Collection<Player> players = new java.util.ArrayList<>();

    @JsonProperty
    private Player requestedPlayer;

    @JsonProperty
    private String lobbyCode;

    @JsonProperty
    private UUID gameId;
}
