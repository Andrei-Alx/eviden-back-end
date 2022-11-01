package nl.fontys.atosgame.cardservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import nl.fontys.atosgame.cardservice.model.Tag;
import nl.fontys.atosgame.cardservice.model.Translation;

import javax.persistence.ElementCollection;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Data
public class CreateCardDto {
    @JsonProperty
    private Collection<Tag> tags;
    @JsonProperty
    private Collection<Translation> translations;
}
