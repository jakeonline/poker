package com.odsinada.icm.straight;

import com.odsinada.icm.Card;
import com.odsinada.icm.PokerHand;
import com.odsinada.icm.PokerHandGrouping;
import com.odsinada.icm.PokerHandUtil;

import java.util.*;
import java.util.stream.Collectors;

public class StraightServiceImpl implements StraightService {

    private static final int COUNT = 1;

    @Override
    public PokerHandGrouping getGroups(PokerHand hand) {
        PokerHandGrouping grouping = new PokerHandGrouping();

        List<PokerHand> combinations = new ArrayList<>();

        Map<String, List<Card>> cardTypeGrouping = PokerHandUtil.getCardTypeGrouping(hand);

        List<Card> straightCards = getStraightCards(cardTypeGrouping);
        if (straightCards != null) {
            combinations.add(new PokerHand(straightCards));
        }

        grouping.setCombination(combinations);

        return grouping;
    }

    private List<Card> getNonFourOfAKindCards(Map<String, List<Card>> cardTypeGrouping) {
        List<Card> nonFourOfAKindCards = new ArrayList<>();
        cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() != COUNT).forEach(t -> nonFourOfAKindCards.addAll(t.getValue()));
        return nonFourOfAKindCards;
    }

    private List<Card> getStraightCards(Map<String, List<Card>> cardTypeGrouping) {
        List<Card> straightCards = new ArrayList<>();

        List<Card> allCards = cardTypeGrouping.entrySet().stream().map(s -> s.getValue().get(0)).collect(Collectors.toList());

        List<Integer> allCardRanks = allCards.stream().map(s -> s.getRank()).collect(Collectors.toList());

        if(isStraight(allCardRanks)){
            straightCards = allCards;
        }

        return straightCards;
    }

    private boolean isStraight(List<Integer> allCardRanks) {
        Collections.sort(allCardRanks);

        Integer previousRank = null;
        boolean isStraight = true;
        for (Integer rank : allCardRanks) {
            if (previousRank == null) {
                previousRank = rank;
            } else {
                if(rank - previousRank != 1){
                    isStraight = false;
                }
                previousRank = rank;
            }
        }
        return isStraight;
    }

    @Override
    public boolean isCombinationPresent(PokerHand hand) {
        Map<String, List<Card>> cardTypeGrouping = PokerHandUtil.getCardTypeGrouping(hand);

        List<Card> straightCards = getStraightCards(cardTypeGrouping);
        if (!straightCards.isEmpty()) {
            return true;
        }
        return false;
    }
}
