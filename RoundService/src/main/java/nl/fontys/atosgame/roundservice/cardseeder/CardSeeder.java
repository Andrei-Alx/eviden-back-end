package nl.fontys.atosgame.roundservice.cardseeder;

import nl.fontys.atosgame.roundservice.enums.TagType;
import nl.fontys.atosgame.roundservice.model.Card;
import nl.fontys.atosgame.roundservice.model.CardSet;
import nl.fontys.atosgame.roundservice.model.Tag;
import nl.fontys.atosgame.roundservice.service.CardService;
import nl.fontys.atosgame.roundservice.service.CardSetEventService;
import nl.fontys.atosgame.roundservice.service.CardSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class CardSeeder {
    private CardService cardService;
    private CardSetService cardSetService;
    private CardSetEventService cardSetEventService;
    private List<CardSet> oldCards;

    public CardSeeder(
            @Autowired CardService cardService,
            @Autowired CardSetService cardSetService,
            @Autowired CardSetEventService cardSetEventService
    ) {
        this.cardService = cardService;
        this.cardSetService = cardSetService;
        this.cardSetEventService = cardSetEventService;
        oldCards = new ArrayList<>(cardSetService.getAllCardSets());
    }

    @EventListener(ApplicationReadyEvent.class)
    public void seedCards() {
        cardSetService.cardSetRequest();
    }

    public void handleCardSet(List<CardSet> currentCards) throws IOException
    {
        CardSet setNeeded = new CardSet();

        for(CardSet set : currentCards)
        {
            if(set.getName().equals("roundOneCards"))
            {
                setNeeded = set;
            }
        }

        List<Tag> tags = AddTags("game", "personal", "color");
        AddCards(setNeeded,"roundOneCards", tags);

        for(CardSet set : currentCards)
        {
            if(set.getName().equals("roundOneCardsAdvice"))
            {
                setNeeded = set;
            }
        }

        tags = AddTags("advice", "personal", "color");
        AddCards(setNeeded, "roundOneCardsAdvice", tags);

        for(CardSet set : currentCards)
        {
            if(set.getName().equals("roundTwoCards"))
            {
                setNeeded = set;
            }
        }

        tags = AddTags("game", "group", "color");
        AddCards(setNeeded, "roundTwoCards", tags);

        for(CardSet set : currentCards)
        {
            if(set.getName().equals("roundTwoCardsAdvice"))
            {
                setNeeded = set;
            }
        }

        tags = AddTags("advice", "group", "color");
        AddCards(setNeeded, "roundTwoCardsAdvice", tags);

        for(CardSet set : currentCards)
        {
            if(set.getName().equals("roundThreeCards"))
            {
                setNeeded = set;
            }
        }

        tags = AddTags("game", "group", "operatingModel");
        AddCards(setNeeded, "roundThreeCards", tags);

        for(CardSet set : currentCards)
        {
            if(set.getName().equals("roundThreeCardsAdvice"))
            {
                setNeeded = set;
            }
        }

        tags = AddTags("advice", "group", "operatingModel");
        AddCards(setNeeded, "roundThreeCardsAdvice", tags);
    }

    public void AddCards(CardSet set, String setName, List<Tag> tags) throws IOException
    {
        if(!oldCards.isEmpty())
        {
            if(ChecksumCheck(setName, (List<Card>)set.getCards()))
            {
                return;
            }
        }

        for (Card card : set.getCards()) {
            cardService.createCard(card);
        }

        cardSetService.createCardSet(set);

        tags.clear();
    }

    public boolean ChecksumCheck(String setName, List<Card> cards)
    {
        if(CreateChecksum(setName, cards))
        {
            return true;
        }

        CardSet currentSet = new CardSet();
        List<UUID> ids = new ArrayList<>();
        for(CardSet set : oldCards)
        {
            if(Objects.equals(set.getName(), setName))
            {
                currentSet = set;
            }
        }

        for(Card card : currentSet.getCards())
        {
            ids.add(card.getId());
        }

        cardSetService.deleteCardSet(currentSet.getId());
        for(UUID id : ids)
        {
            cardService.deleteCard(id);
        }

        return false;
    }

    public boolean CreateChecksum(String setName, List<Card> cards)
    {
        List<Card> cardSetOld = new ArrayList<>();

        List<String> tags = new ArrayList<>();

        for(CardSet set : oldCards)
        {
            if(Objects.equals(set.getName(), setName))
            {
                cardSetOld = new ArrayList<>(set.getCards());
            }
        }

        for(Card card : cardSetOld)
        {
            tags.add(new ArrayList<>(card.getTags()).get(0).getTagValue());
        }

        Collections.sort(tags);

        int hashOld = tags.hashCode();

        tags.clear();

        for(Card card : cards)
        {
            tags.add(new ArrayList<>(card.getTags()).get(0).getTagValue());
        }

        Collections.sort(tags);

        int hashNew = tags.hashCode();

        if(hashOld == hashNew)
        {
            return true;
        }
        else{
            return false;
        }
    }

    public List<Tag> AddTags(String value1, String value2, String value3)
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

}
