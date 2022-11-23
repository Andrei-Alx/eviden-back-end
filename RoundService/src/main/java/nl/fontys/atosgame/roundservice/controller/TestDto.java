package nl.fontys.atosgame.roundservice.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestDto {
    public String playerId;
    public String roundId;
    public List<String> cardIds;
    public String gameId;
}
