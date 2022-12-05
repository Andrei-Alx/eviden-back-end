package nl.fontys.atosgame.lobbyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LobbyQuitDto {

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID lobbyId;
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID playerId;
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID gameId;
}
