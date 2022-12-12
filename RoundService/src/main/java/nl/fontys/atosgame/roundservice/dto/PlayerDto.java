package nl.fontys.atosgame.roundservice.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private String name;
}
