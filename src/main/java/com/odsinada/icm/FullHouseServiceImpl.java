package com.odsinada.icm;

import java.util.*;

public class FullHouseServiceImpl implements FullHouseService {
    @Override
    public FullHouseGroups getGroups(PokerHand hand) {
        FullHouseGroups groups = new FullHouseGroups();

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

        Optional<Map.Entry<Integer, List<Card>>> combinationThreeOfAKind = cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() == 3).findFirst();
        if (combinationThreeOfAKind.isPresent()) {
            combinations.add(new PokerHand(combinationThreeOfAKind.get().getValue()));
        }

        Optional<Map.Entry<Integer, List<Card>>> combinationPair = cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() == 2).findFirst();
        if (combinationPair.isPresent()) {
            combinations.add(new PokerHand(combinationPair.get().getValue()));
        }

        groups.setCombination(combinations);

        return groups;
    }
}
