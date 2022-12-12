package nl.fontys.atosgame.gameappbff.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LobbyResponseDto {

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID lobbyId;

    private String code;
}
