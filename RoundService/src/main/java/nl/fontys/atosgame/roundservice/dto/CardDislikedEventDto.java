package nl.fontys.atosgame.roundservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDislikedEventDto {
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID playerId;
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID gameId;
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID roundId;
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID cardId;
}
