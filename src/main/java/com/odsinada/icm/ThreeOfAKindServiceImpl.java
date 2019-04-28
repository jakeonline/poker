package com.odsinada.icm;

import java.util.*;

public class ThreeOfAKindServiceImpl implements ThreeOfAKindService {
    @Override
    public ThreeOfAKindGroups getGroups(PokerHand hand) {
        ThreeOfAKindGroups groups = new ThreeOfAKindGroups();

        List<PokerHand> combinations = new ArrayList<>();

        Map<Integer, List<Card>> cardTypeGrouping = new HashMap<>();
        for (Card eachCard : hand.getCards()) {

            List<Card> cardList = cardTypeGrouping.get(eachCard.getRank());
            if (cardList == null) {
                cardList = new ArrayList<>();
                cardTypeGrouping.put(eachCard.getRank(), cardList);
            }

            cardList.add(eachCard);

        }

        Optional<Map.Entry<Integer, List<Card>>> combinationGrouping = cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() == 3).findFirst();
        if(combinationGrouping.isPresent()){
            combinations.add(new PokerHand(combinationGrouping.get().getValue()));
        }

        List<Card> nonCombinationCards = new ArrayList<>();
        cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() != 3).forEach(t -> nonCombinationCards.addAll(t.getValue()));

        groups.setCombination(combinations);
        groups.setNonCombination(new PokerHand(nonCombinationCards));

        return groups;
    }
}
