package nl.fontys.atosgame.cardservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @JsonProperty
    private String importantTag;
}
