package nl.fontys.atosgame.cardservice.event;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardSetDeletedData extends EventData{
    private UUID cardSetId;
}
