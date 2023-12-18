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
    private List<CardSet> oldCards;

    public CardSeeder(
            @Autowired CardService cardService,
            @Autowired CardSetService cardSetService
    ) {
        this.cardService = cardService;
        this.cardSetService = cardSetService;
    }

    public void handleCardSet(List<CardSet> currentCards) throws IOException {
        oldCards = new ArrayList<>(cardSetService.getAllCardSets());
        //for every card-set in currentCards get the set and try to add cards
        for (CardSet set : currentCards) {
            List<Tag> tags;
            if (set.getName().equals("roundOneCards")) {
                tags = AddTags("game", "personal", "color");
                AddCards(set, "roundOneCards", tags);
            }

            if (set.getName().equals("roundOneCardsAdvice")) {
                tags = AddTags("advice", "personal", "color");
                AddCards(set, "roundOneCardsAdvice", tags);
            }

            if (set.getName().equals("roundTwoCards")) {
                tags = AddTags("game", "group", "color");
                AddCards(set, "roundTwoCards", tags);
            }

            if (set.getName().equals("roundTwoCardsAdvice")) {
                tags = AddTags("advice", "group", "color");
                AddCards(set, "roundTwoCardsAdvice", tags);
            }

            if (set.getName().equals("roundThreeCards")) {
                tags = AddTags("game", "group", "operatingModel");
                AddCards(set, "roundThreeCards", tags);
            }

            if (set.getName().equals("roundThreeCardsAdvice")) {
                tags = AddTags("advice", "group", "operatingModel");
                AddCards(set, "roundThreeCardsAdvice", tags);
            }
        }
    }

    public void AddCards(CardSet receivedSet, String setName, List<Tag> tags) {
        //If there are cards in the database do the checksum
        if (!oldCards.isEmpty()) {
            //full list of old cards that will be used to update the set if needed
            CardSet cardSetOld = new CardSet();
            //starts as the list of old cards but will remove have all cards removed that exist in received set
            CardSet cardSetOldToBeDisabled = new CardSet();
            for (CardSet set : oldCards) {
                if (Objects.equals(set.getName(), setName)) {
                    cardSetOld = set;
                    cardSetOldToBeDisabled = set;
                }
            }

            //compare new set to old set to check for changes
            if (receivedSet.equals(cardSetOld)) {
                return;
            }

            //loop through every card in the new set
            for (Card cardNew : receivedSet.getCards()) {
                //try to find the same card in old set
                for (Card cardOld : cardSetOld.getCards()) {
                    //compare the two cards
                    boolean found = cardNew.getId() == cardOld.getId();
                    if (found) {
                        // if the card is found it is removed from these 2 lists
                        cardSetOldToBeDisabled.getCards().remove(cardOld);
                        receivedSet.getCards().remove(cardNew);
                        return;
                    }
                }
            }

            //all remaining cards have to be disabled from this list
            for (Card card : cardSetOldToBeDisabled.getCards()) {
                card.setActive(false);
                cardService.updateCard(card);
            }

            //all remaining cards in this list have to be created
            for (Card card : receivedSet.getCards()) {
                cardService.createCard(card);
                cardSetOld.getCards().add(card);
            }

            //update the cardset to link with the new cards
            cardSetService.updateCardSet(cardSetOld);
        } else {
            //if there are no old cards this will be executed
            //Insert received cards into the db
            for (Card card : receivedSet.getCards()) {
                cardService.createCard(card);
            }
            //Insert received card-set into the db
            cardSetService.createCardSet(receivedSet);
        }
        //clear the tags for next function
        tags.clear();
    }

    private List<Tag> AddTags(String value1, String value2, String value3) {
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
