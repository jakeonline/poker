package com.odsinada.icm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokerHandUtil {

    public static Map<String, List<Card>> getCardTypeGrouping(PokerHand hand) {
        Map<String, List<Card>> cardTypeGrouping = new HashMap<>();
        for (Card eachCard : hand.getCards()) {

            List<Card> cardList = cardTypeGrouping.get(eachCard.getType());
            if (cardList == null) {
                cardList = new ArrayList<>();
                cardTypeGrouping.put(eachCard.getType(), cardList);
            }

            cardList.add(eachCard);

        }

        return cardTypeGrouping;
    }
}
