package nl.fontys.atosgame.cardservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCardSetDto {
    @JsonProperty
    private String name;
    @JsonProperty
    private String type;
    @JsonProperty
    private Collection<UUID> cards;
}
