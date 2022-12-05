package nl.fontys.atosgame.roundservice.dto;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardSubmitRequestDto {
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID playerId;
    @Type(type = "org.hibernate.type.UUIDCharType")
    private List<UUID> cardIds;
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID roundId;
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID gameId;
}
