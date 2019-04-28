package com.odsinada.icm.fullhouse;

import com.odsinada.icm.Card;
import com.odsinada.icm.PokerHand;
import com.odsinada.icm.PokerHandGrouping;
import com.odsinada.icm.PokerHandUtil;

import java.util.*;

public class FullHouseServiceImpl implements FullHouseService {
    @Override
    public PokerHandGrouping getGroups(PokerHand hand) {
        PokerHandGrouping groups = new PokerHandGrouping();

        List<PokerHand> combinations = new ArrayList<>();

        Map<String, List<Card>> cardTypeGrouping = PokerHandUtil.getCardTypeGrouping(hand);

        List<Card> threeOfAKindCards = getThreeOfAKindCards(cardTypeGrouping);
        if (threeOfAKindCards != null) {
            combinations.add(new PokerHand(threeOfAKindCards));
        }

        List<Card> pairCards = getPairCards(cardTypeGrouping);
        if (pairCards != null) {
            combinations.add(new PokerHand(pairCards));
        }

        groups.setCombination(combinations);

        return groups;
    }

    private List<Card> getThreeOfAKindCards(Map<String, List<Card>> cardTypeGrouping) {
        List<Card> threeOfAKindCards = null;
        Optional<Map.Entry<String, List<Card>>> threeOfAKindGroup = cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() == 3).findFirst();

        if(threeOfAKindGroup.isPresent()){
            threeOfAKindCards = new ArrayList<>(threeOfAKindGroup.get().getValue());
        }

        return threeOfAKindCards;
    }

    private List<Card> getPairCards(Map<String, List<Card>> cardTypeGrouping) {
        List<Card> pairCards = null;
        Optional<Map.Entry<String, List<Card>>> pairGroup = cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() == 2).findFirst();

        if(pairGroup.isPresent()){
            pairCards = new ArrayList<>(pairGroup.get().getValue());
        }

        return pairCards;
    }

    @Override
    public boolean isCombinationPresent(PokerHand hand) {
        Map<String, List<Card>> cardTypeGrouping = PokerHandUtil.getCardTypeGrouping(hand);

        List<Card> threeOfAKindCards = getThreeOfAKindCards(cardTypeGrouping);
        List<Card> pairCards = getPairCards(cardTypeGrouping);

        if (threeOfAKindCards != null && pairCards != null) {
            return true;
        }

        return false;
    }
}
