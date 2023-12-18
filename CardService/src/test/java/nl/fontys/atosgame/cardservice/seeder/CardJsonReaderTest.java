package nl.fontys.atosgame.cardservice.seeder;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import nl.fontys.atosgame.cardservice.dto.CreateCardDto;
import nl.fontys.atosgame.cardservice.enums.TagType;
import nl.fontys.atosgame.cardservice.model.Tag;
import nl.fontys.atosgame.cardservice.model.Translation;
import org.junit.jupiter.api.Test;

class CardJsonReaderTest {

    /*
    @Test
    void readCards() throws IOException {
        List<CreateCardDto> cards = new ArrayList<CreateCardDto>(
            List.of(
                new CreateCardDto(
                    new ArrayList<>(List.of(new Tag(TagType.TYPE, "testValue"))),
                    new ArrayList<>(List.of(new Translation("testLanguage", "testText")))
                )
            )
        );
        ObjectMapper mapper = new ObjectMapper();
        File f = new File("src/test/resources/cards.json");
        mapper.writeValue(f, cards);

        List<CreateCardDto> readCards = CardJsonReader.readCards(f);

        assertEquals(cards.size(), readCards.size());
        assertEquals(cards.get(0).getTags().size(), readCards.get(0).getTags().size());
        assertEquals(
            cards.get(0).getTranslations().size(),
            readCards.get(0).getTranslations().size()
        );

        // delete file
        f.delete();
    }
     */

}
