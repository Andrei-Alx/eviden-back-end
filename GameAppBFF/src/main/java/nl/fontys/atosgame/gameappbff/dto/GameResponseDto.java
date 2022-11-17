package nl.fontys.atosgame.gameappbff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameResponseDto {

    private String id;
    private LobbyResponseDto lobby;
    private String companyType;
}
