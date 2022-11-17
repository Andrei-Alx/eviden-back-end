package nl.fontys.atosgame.gameservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Tag {

    @JsonProperty
    private String tagKey;

    @JsonProperty
    private String tagValue;
}
