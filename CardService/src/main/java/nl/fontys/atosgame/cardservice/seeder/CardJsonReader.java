package nl.fontys.atosgame.cardservice.seeder;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

import nl.fontys.atosgame.cardservice.dto.CreateCardDto;

public class CardJsonReader {

    public static java.util.List<CreateCardDto> readCards(InputStream file) {
        ObjectMapper mapper = new ObjectMapper();
        // Read json
        try {
            return mapper.readValue(
                file,
                mapper
                    .getTypeFactory()
                    .constructCollectionType(java.util.List.class, CreateCardDto.class)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
