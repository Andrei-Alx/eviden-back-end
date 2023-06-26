package nl.fontys.atosgame.roundservice.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

// OBSOLETED
public class PlayerRoundEndedDto {

    public PlayerRoundEndedDto(UUID gameId, UUID roundId, UUID playerId) {}

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class RoundEndedDto {

        @Type(type = "org.hibernate.type.UUIDCharType")
        private UUID gameId;

        @Type(type = "org.hibernate.type.UUIDCharType")
        private UUID roundId;

        @Type(type = "org.hibernate.type.UUIDCharType")
        private UUID playerId;
    }
}
