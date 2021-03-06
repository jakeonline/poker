package com.odsinada.icm.straight;

import com.odsinada.icm.Card;
import com.odsinada.icm.PokerHand;
import com.odsinada.icm.PokerHandGrouping;
import com.odsinada.icm.PokerHandUtil;

import java.util.*;
import java.util.stream.Collectors;

public class StraightServiceImpl implements StraightService {

    @Override
    public PokerHandGrouping getGroups(PokerHand hand) {
        PokerHandGrouping grouping = new PokerHandGrouping();

        List<PokerHand> combinations = new ArrayList<>();

        Map<String, List<Card>> cardTypeGrouping = PokerHandUtil.getCardTypeGrouping(hand);

        List<Card> straightCards = StraightServiceUtil.getStraightCards(cardTypeGrouping);
        if (straightCards != null) {
            combinations.add(new PokerHand(straightCards));
        }

        grouping.setCombination(combinations);

        return grouping;
    }

    @Override
    public boolean isCombinationPresent(PokerHand hand) {
        Map<String, List<Card>> cardTypeGrouping = PokerHandUtil.getCardTypeGrouping(hand);

        List<Card> straightCards = StraightServiceUtil.getStraightCards(cardTypeGrouping);
        if (!straightCards.isEmpty()) {
            return true;
        }
        return false;
    }
}
