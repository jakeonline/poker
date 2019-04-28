package com.odsinada.icm;

import java.util.*;

public class ThreeOfAKindServiceImpl implements ThreeOfAKindService {

    public static final int COUNT = 3;

    @Override
    public PokerHandGrouping getGroups(PokerHand hand) {
        PokerHandGrouping grouping = new PokerHandGrouping();

        List<PokerHand> combinations = new ArrayList<>();

        Map<String, List<Card>> cardTypeGrouping = PokerHandUtil.getCardTypeGrouping(hand);

        List<Card> threeOfAKindCards = getThreeOfAKindCards(cardTypeGrouping);
        if (threeOfAKindCards != null) {
            combinations.add(new PokerHand(threeOfAKindCards));
        }

        List<Card> nonThreeOfAKindCards = getNonThreeOfAKindCards(cardTypeGrouping);

        grouping.setCombination(combinations);
        grouping.setNonCombination(new PokerHand(nonThreeOfAKindCards));

        return grouping;
    }

    private List<Card> getNonThreeOfAKindCards(Map<String, List<Card>> cardTypeGrouping) {
        List<Card> nonThreeOfAKindCards = new ArrayList<>();
        cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() != COUNT).forEach(t -> nonThreeOfAKindCards.addAll(t.getValue()));
        return nonThreeOfAKindCards;
    }

    private List<Card> getThreeOfAKindCards(Map<String, List<Card>> cardTypeGrouping) {
        List<Card> threeOfAKindCards = null;
        Optional<Map.Entry<String, List<Card>>> threeOfAKindGroup = cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() == 3).findFirst();

        if(threeOfAKindGroup.isPresent()){
            threeOfAKindCards = new ArrayList<>(threeOfAKindGroup.get().getValue());
        }

        return threeOfAKindCards;
    }

    @Override
    public boolean isCombinationPresent(PokerHand hand) {
        Map<String, List<Card>> cardTypeGrouping = PokerHandUtil.getCardTypeGrouping(hand);

        List<Card> threeOfAKindCards = getThreeOfAKindCards(cardTypeGrouping);
        if (threeOfAKindCards != null) {
            return true;
        }
        return false;
    }
}
