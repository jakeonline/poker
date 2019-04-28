package com.odsinada.icm;

import java.util.*;

public class FourOfAKindServiceImpl implements FourOfAKindService {

    @Override
    public FourOfAKindGroups getGroups(HandBase hand) {
        FourOfAKindGroups groups = new FourOfAKindGroups();

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

        Optional<Map.Entry<Integer, List<Card>>> combinationFourOfAKind = cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() == 4).findFirst();
        if (combinationFourOfAKind.isPresent()) {
            combinations.add(new HandBase(combinationFourOfAKind.get().getValue()));
        }

        List<Card> nonCombinationCards = new ArrayList<>();
        cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() != 4).forEach(t -> nonCombinationCards.addAll(t.getValue()));

        groups.setCombination(combinations);
        groups.setNonCombination(new HandBase(nonCombinationCards));

        return groups;
    }
}
