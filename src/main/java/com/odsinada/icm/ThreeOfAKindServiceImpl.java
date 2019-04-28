package com.odsinada.icm;

import java.util.*;

public class ThreeOfAKindServiceImpl implements ThreeOfAKindService {
    @Override
    public ThreeOfAKindGroups getGroups(HandBase hand) {
        ThreeOfAKindGroups groups = new ThreeOfAKindGroups();

        List<HandBase> combinations = new ArrayList<>();

        Map<Integer, List<Card>> cardTypeGrouping = new HashMap<>();
        for (Card eachCard : hand.getCards()) {

            List<Card> cardList = cardTypeGrouping.get(eachCard.getOrder());
            if (cardList == null) {
                cardList = new ArrayList<>();
                cardTypeGrouping.put(eachCard.getOrder(), cardList);
            }

            cardList.add(eachCard);

        }

        Optional<Map.Entry<Integer, List<Card>>> combinationGrouping = cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() == 3).findFirst();
        if(combinationGrouping.isPresent()){
            combinations.add(new HandBase(combinationGrouping.get().getValue()));
        }

        List<Card> nonCombinationCards = new ArrayList<>();
        cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() != 3).forEach(t -> nonCombinationCards.addAll(t.getValue()));

        groups.setCombination(combinations);
        groups.setNonCombination(new HandBase(nonCombinationCards));

        return groups;
    }
}