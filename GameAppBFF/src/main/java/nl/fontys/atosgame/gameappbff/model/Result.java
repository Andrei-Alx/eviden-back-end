package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import java.util.UUID;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.enums.ShowResults;
import org.hibernate.annotations.Type;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @JsonProperty
    private UUID id;

    @JsonProperty
    private ShowResults type;

    @JsonProperty
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID playerId;

    @ElementCollection(fetch = javax.persistence.FetchType.EAGER)
    @JsonProperty
    private Collection<Tag> tags = new java.util.ArrayList<>();

    @JsonProperty
    private String status;
}
