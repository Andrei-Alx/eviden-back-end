package nl.fontys.atosgame.cardservice.seeder;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.fontys.atosgame.cardservice.dto.CreateCardDto;

import java.io.File;

public class CardJsonReader {
    public static java.util.List<CreateCardDto> readCards(File file) {
        ObjectMapper mapper = new ObjectMapper();
        // Read json
        try {
            return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(java.util.List.class, CreateCardDto.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
