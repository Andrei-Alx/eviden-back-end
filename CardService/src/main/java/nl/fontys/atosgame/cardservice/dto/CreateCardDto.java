package nl.fontys.atosgame.cardservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.cardservice.model.Tag;
import nl.fontys.atosgame.cardservice.model.Translation;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCardDto {

    @JsonProperty
    private List<Tag> tags;

    @JsonProperty
    private List<Translation> translations;
}
