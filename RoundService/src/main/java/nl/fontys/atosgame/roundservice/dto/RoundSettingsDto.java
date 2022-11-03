package nl.fontys.atosgame.roundservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

public class RoundSettingsDto {
    @Type(type="org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID id;

    @JsonProperty
    private boolean showPersonalOrGroupResults;

    @JsonProperty
    private int nrOfLikedCards;

    @JsonProperty
    private int nrOfPickedCards;

    @JsonProperty
    private String shuffleMethod;

    @JsonProperty
    private boolean showSameCardOrder;

    @JsonProperty
    private UUID cardSetId;
}
