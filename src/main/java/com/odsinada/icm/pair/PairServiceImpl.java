package com.odsinada.icm.pair;

import com.odsinada.icm.Card;
import com.odsinada.icm.PokerHand;
import com.odsinada.icm.PokerHandGrouping;
import com.odsinada.icm.PokerHandUtil;

import java.util.*;

public class PairServiceImpl implements PairService {

    public static final int COUNT = 2;

    @Override
    public PokerHandGrouping getGroups(PokerHand hand) {
        PokerHandGrouping grouping = new PokerHandGrouping();

        List<PokerHand> combinations = new ArrayList<>();

        Map<String, List<Card>> cardTypeGrouping = PokerHandUtil.getCardTypeGrouping(hand);

        List<Card> pairCards = getPairCards(cardTypeGrouping);
        if (pairCards != null) {
            combinations.add(new PokerHand(pairCards));
        }

        List<Card> nonPairCards = getNonPairCards(cardTypeGrouping);

        grouping.setCombination(combinations);
        grouping.setNonCombination(new PokerHand(nonPairCards));

        return grouping;
    }

    @Override
    public boolean isCombinationPresent(PokerHand hand) {
        Map<String, List<Card>> cardTypeGrouping = PokerHandUtil.getCardTypeGrouping(hand);

        List<Card> pairCards = getPairCards(cardTypeGrouping);
        if (pairCards != null) {
            return true;
        }
        return false;
    }

    private List<Card> getNonPairCards(Map<String, List<Card>> cardTypeGrouping) {
        List<Card> nonPairCards = new ArrayList<>();
        cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() != COUNT).forEach(t -> nonPairCards.addAll(t.getValue()));
        return nonPairCards;
    }

    private List<Card> getPairCards(Map<String, List<Card>> cardTypeGrouping) {
        List<Card> pairCards = null;
        Optional<Map.Entry<String, List<Card>>> pairGroup = cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() == COUNT).findFirst();
        if(pairGroup.isPresent()){
            pairCards = new ArrayList<>(pairGroup.get().getValue());
        }

        return pairCards;
    }

}
