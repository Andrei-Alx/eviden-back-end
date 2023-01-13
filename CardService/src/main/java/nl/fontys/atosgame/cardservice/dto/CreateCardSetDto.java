package nl.fontys.atosgame.cardservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.UUID;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.cardservice.enums.CardSetType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCardSetDto {

    @JsonProperty
    private String name;

    @JsonProperty
    @Enumerated(EnumType.STRING)
    private CardSetType type;

    @JsonProperty
    private Collection<UUID> cards;

    @JsonProperty
    private String importantTag;
}
