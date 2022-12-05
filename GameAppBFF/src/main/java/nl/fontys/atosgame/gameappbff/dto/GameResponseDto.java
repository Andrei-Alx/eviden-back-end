package nl.fontys.atosgame.gameappbff.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameResponseDto {

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID gameId;
    private LobbyResponseDto lobby;
    private String title;
    private String companyType;
}
