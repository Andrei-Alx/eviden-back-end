package nl.fontys.atosgame.gameappbff.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.enums.ResultStatus;
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
    private Collection<Tag> tags = new ArrayList<>();

    @JsonProperty
    private ResultStatus status;
}
