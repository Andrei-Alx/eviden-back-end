package nl.fontys.atosgame.cardservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.cardservice.enums.CardSetType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCardSetDto {

    @JsonProperty
    private CardSetType type;

    @JsonProperty
    private Collection<UUID> cards;

    @JsonProperty
    private String importantTag;
}
