package com.odsinada.icm;

import java.util.*;

public class PairServiceImpl implements PairService {

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

    private List<Card> getNonPairCards(Map<String, List<Card>> cardTypeGrouping) {
        List<Card> nonPairCards = new ArrayList<>();
        cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() != 2).forEach(t -> nonPairCards.addAll(t.getValue()));
        return nonPairCards;
    }

    private List<Card> getPairCards(Map<String, List<Card>> cardTypeGrouping) {
        List<Card> pairCards = null;
        Optional<Map.Entry<String, List<Card>>> pairGroup = cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() == 2).findFirst();
        if(pairGroup.isPresent()){
            pairCards = new ArrayList<>(pairGroup.get().getValue());
        }

        return pairCards;
    }
}
