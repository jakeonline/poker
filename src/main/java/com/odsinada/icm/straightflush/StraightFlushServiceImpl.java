package com.odsinada.icm.straightflush;

import com.odsinada.icm.Card;
import com.odsinada.icm.PokerHand;
import com.odsinada.icm.PokerHandGrouping;
import com.odsinada.icm.PokerHandUtil;
import com.odsinada.icm.flush.FlushServiceUtil;
import com.odsinada.icm.straight.StraightServiceUtil;

import java.util.*;
import java.util.stream.Collectors;

public class StraightFlushServiceImpl implements StraightFlushService {

    @Override
    public PokerHandGrouping getGroups(PokerHand hand) {
        PokerHandGrouping grouping = new PokerHandGrouping();

        List<PokerHand> combinations = new ArrayList<>();

        Map<String, List<Card>> cardTypeGrouping = PokerHandUtil.getCardTypeGrouping(hand);

        List<Card> straightCards = StraightServiceUtil.getStraightCards(cardTypeGrouping);

        if (!straightCards.isEmpty() && FlushServiceUtil.getSuits(cardTypeGrouping).size() == 1) {
            combinations.add(new PokerHand(straightCards));
        }

        grouping.setCombination(combinations);

        return grouping;
    }

        @Override
    public boolean isCombinationPresent(PokerHand hand) {
        Map<String, List<Card>> cardTypeGrouping = PokerHandUtil.getCardTypeGrouping(hand);

        List<Card> straightCards = StraightServiceUtil.getStraightCards(cardTypeGrouping);
        if (!straightCards.isEmpty() && FlushServiceUtil.getSuits(cardTypeGrouping).size() == 1) {
            return true;
        }
        return false;
    }
}
