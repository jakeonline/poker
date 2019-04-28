package com.odsinada.icm;

import java.util.*;

public class FourOfAKindServiceImpl implements FourOfAKindService {

    @Override
    public FourOfAKindGroups getGroups(PokerHand hand) {
        FourOfAKindGroups groups = new FourOfAKindGroups();

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

        Optional<Map.Entry<Integer, List<Card>>> combinationFourOfAKind = cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() == 4).findFirst();
        if (combinationFourOfAKind.isPresent()) {
            combinations.add(new PokerHand(combinationFourOfAKind.get().getValue()));
        }

        List<Card> nonCombinationCards = new ArrayList<>();
        cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() != 4).forEach(t -> nonCombinationCards.addAll(t.getValue()));

        groups.setCombination(combinations);
        groups.setNonCombination(new PokerHand(nonCombinationCards));

        return groups;
    }
}
