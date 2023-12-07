package nl.fontys.atosgame.gameappbff.cardseeder;

import nl.fontys.atosgame.gameappbff.enums.TagType;
import nl.fontys.atosgame.gameappbff.model.Card;
import nl.fontys.atosgame.gameappbff.model.CardSet;
import nl.fontys.atosgame.gameappbff.model.Tag;
import nl.fontys.atosgame.gameappbff.service.CardService;
import nl.fontys.atosgame.gameappbff.service.CardSetEventService;
import nl.fontys.atosgame.gameappbff.service.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class CardSeeder {
    private final CardService cardService;
    private final CardSetService cardSetService;
    private final List<CardSet> oldCards;

    public CardSeeder(
            @Autowired CardService cardService,
            @Autowired CardSetService cardSetService
            ) {
        this.cardService = cardService;
        this.cardSetService = cardSetService;
        oldCards = new ArrayList<>(cardSetService.getAllCardSets());
    }

    public void handleCardSet(List<CardSet> currentCards) throws IOException
    {
        //for every card-set in currentCards get the set and check cards
        for(CardSet set : currentCards)
        {
            List<Tag> tags;
            if(set.getName().equals("roundOneCards"))
            {
                tags = AddTags("game", "personal", "color");
                AddCards(set,"roundOneCards", tags);
            }

            if(set.getName().equals("roundOneCardsAdvice"))
            {
                tags = AddTags("advice", "personal", "color");
                AddCards(set, "roundOneCardsAdvice", tags);
            }

            if(set.getName().equals("roundTwoCards"))
            {
                tags = AddTags("game", "group", "color");
                AddCards(set, "roundTwoCards", tags);
            }

            if(set.getName().equals("roundTwoCardsAdvice"))
            {
                tags = AddTags("advice", "group", "color");
                AddCards(set, "roundTwoCardsAdvice", tags);
            }

            if(set.getName().equals("roundThreeCards"))
            {
                tags = AddTags("game", "group", "operatingModel");
                AddCards(set, "roundThreeCards", tags);
            }

            if(set.getName().equals("roundThreeCardsAdvice"))
            {
                tags = AddTags("advice", "group", "operatingModel");
                AddCards(set, "roundThreeCardsAdvice", tags);
            }
        }
    }

    public void AddCards(CardSet receivedSet, String setName, List<Tag> tags) throws IOException
    {
        //If there are cards in the database do the checksum
        if(!oldCards.isEmpty())
        {
            if(Checksum(setName, (List<Card>)receivedSet.getCards()))
            {
                return;
            }

            //Get the currently checked set from the old card-sets list
            CardSet currentSet = new CardSet();
            List<UUID> ids = new ArrayList<>();
            for(CardSet set : oldCards)
            {
                if(Objects.equals(set.getName(), setName))
                {
                    currentSet = set;
                }
            }

            //retrieve all card ids from the current set
            for(Card card : currentSet.getCards())
            {
                ids.add(card.getId());
            }

            //Delete the incorrect card-set and associated cards
            cardSetService.deleteCardSet(currentSet.getId());
            for(UUID id : ids)
            {
                cardService.deleteCard(id);
            }
        }

        //Insert received cards into the db
        for (Card card : receivedSet.getCards()) {
            cardService.createCard(card);
        }

        //Insert received card-set into the db
        cardSetService.createCardSet(receivedSet);

        //clear the tags for next function
        tags.clear();
    }

    private boolean Checksum(String setName, List<Card> newCards)
    {
        //Get the current old set from the list of old sets
        List<Card> cardSetOld = new ArrayList<>();
        for(CardSet set : oldCards)
        {
            if(Objects.equals(set.getName(), setName))
            {
                cardSetOld = new ArrayList<>(set.getCards());
            }
        }

        //Create a hash of old set and new set, compare and return result
        return CreateHash(cardSetOld) == CreateHash(newCards);
    }

    private List<Tag> AddTags(String value1, String value2, String value3)
    {
        List<Tag> tags = new ArrayList<>();
        Tag tag1 = new Tag();
        // type tag
        tag1.setTagKey(TagType.TYPE);
        tag1.setTagValue(value1);

        // group or personal tag
        Tag tag2 = new Tag();
        tag2.setTagKey(TagType.GROUP_OR_PERSONAL);
        tag2.setTagValue(value2);

        // important tag
        Tag tag3 = new Tag();
        tag3.setTagKey(TagType.IMPORTANT_TAG);
        tag3.setTagValue(value3);

        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);

        return tags;
    }

    private int CreateHash(List<Card> setToHash)
    {
        //Create new lists to hold the card variables
        List<String> dutchCards = new ArrayList<>();
        List<String> englishCards = new ArrayList<>();
        List<String> tags = new ArrayList<>();

        //Get the translations + tags from the cards
        for(Card card : setToHash)
        {
            dutchCards.add((new ArrayList<>(card.getTranslations()).get(0).getText()));
            englishCards.add((new ArrayList<>(card.getTranslations()).get(1).getText()));
            tags.add(new ArrayList<>(card.getTags()).get(0).getTagValue());
        }

        //Sort them to make sure both hashes are made from the same ordering
        Collections.sort(dutchCards);
        Collections.sort(englishCards);
        Collections.sort(tags);

        return dutchCards.hashCode() + englishCards.hashCode() + tags.hashCode();
    }
}
