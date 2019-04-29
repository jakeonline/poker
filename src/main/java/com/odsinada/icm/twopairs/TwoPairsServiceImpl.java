package com.odsinada.icm.twopairs;

import com.odsinada.icm.*;

import java.util.*;

public class TwoPairsServiceImpl implements TwoPairsService {
    private static final int COUNT = 2;

    @Override
    public PokerHandGrouping getGroups(PokerHand hand) {
        PokerHandGrouping grouping = new PokerHandGrouping();

        List<PokerHand> combinations = new ArrayList<>();

        Map<String, List<Card>> cardTypeGrouping = PokerHandUtil.getCardTypeGrouping(hand);

        List<List<Card>> pairsOfCards = getPairsOfCards(cardTypeGrouping);
        if (!pairsOfCards.isEmpty()) {
            combinations.add(new PokerHand(pairsOfCards.get(0)));
            combinations.add(new PokerHand(pairsOfCards.get(1)));
        }

        List<Card> nonPairCards = getNonPairCards(cardTypeGrouping);

        grouping.setCombination(combinations);
        grouping.setNonCombination(new PokerHand(nonPairCards));

        return grouping;
    }

    @Override
    public boolean isCombinationPresent(PokerHand hand) {
        Map<String, List<Card>> cardTypeGrouping = PokerHandUtil.getCardTypeGrouping(hand);

        List<List<Card>> pairsOfCards = getPairsOfCards(cardTypeGrouping);
        if (pairsOfCards.size() == 2) {
            return true;
        }
        return false;
    }

    private List<Card> getNonPairCards(Map<String, List<Card>> cardTypeGrouping) {
        List<Card> nonPairCards = new ArrayList<>();
        cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() != COUNT).forEach(t -> nonPairCards.addAll(t.getValue()));
        return nonPairCards;
    }

    private List<List<Card>> getPairsOfCards(Map<String, List<Card>> cardTypeGrouping) {
        List<List<Card>> pairsOfCards = new ArrayList<>();

        Iterator<Map.Entry<String, List<Card>>> pairsIter = cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() == COUNT).iterator();

        if(pairsIter.hasNext()){
            Map.Entry<String, List<Card>> pairOne = pairsIter.next();
            pairsOfCards.add(pairOne.getValue());
        }

        if(pairsIter.hasNext()){
            Map.Entry<String, List<Card>> pairTwo = pairsIter.next();
            pairsOfCards.add(pairTwo.getValue());
        }

        return pairsOfCards;
    }
}
