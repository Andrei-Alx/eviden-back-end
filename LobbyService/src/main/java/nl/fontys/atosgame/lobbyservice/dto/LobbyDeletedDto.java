package nl.fontys.atosgame.lobbyservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LobbyDeletedDto {

    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID lobbyId;

    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID gameId;
}
