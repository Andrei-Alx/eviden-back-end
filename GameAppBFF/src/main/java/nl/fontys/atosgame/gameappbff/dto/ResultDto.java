package nl.fontys.atosgame.gameappbff.dto;

import java.util.Collection;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.fontys.atosgame.gameappbff.enums.ResultStatus;
import nl.fontys.atosgame.gameappbff.enums.ShowResults;
import nl.fontys.atosgame.gameappbff.model.Tag;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {

    private UUID playerId;
    private ShowResults type;
    private Collection<Tag> tags;
    private ResultStatus status;
}
