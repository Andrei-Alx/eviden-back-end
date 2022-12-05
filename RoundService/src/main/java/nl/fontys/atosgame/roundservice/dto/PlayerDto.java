package nl.fontys.atosgame.roundservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {

    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    private String name;
}
