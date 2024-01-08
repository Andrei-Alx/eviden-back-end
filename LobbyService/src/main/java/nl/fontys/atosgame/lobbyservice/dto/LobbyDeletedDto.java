package nl.fontys.atosgame.lobbyservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LobbyDeletedDto {

    @JsonProperty
    private UUID lobbyId;

    @JsonProperty
    private UUID gameId;
}
