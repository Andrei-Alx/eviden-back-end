package nl.fontys.atosgame.roundservice.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerPhaseStartedDto {

    private int phaseNumber;

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID playerId;

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID gameId;

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID roundId;
}
