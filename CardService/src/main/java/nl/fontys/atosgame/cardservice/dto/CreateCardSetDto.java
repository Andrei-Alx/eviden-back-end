package nl.fontys.atosgame.cardservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.cardservice.model.Card;
import nl.fontys.atosgame.cardservice.model.Tag;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCardSetDto {

    @JsonProperty
    private String name;

    @JsonProperty
    private List<Tag> tags;

    @JsonProperty
    private List<Card> cards;
}
