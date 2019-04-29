package com.odsinada.icm.flush;

import com.odsinada.icm.*;

import java.util.*;
import java.util.stream.Collectors;

public class FlushServiceImpl implements FlushService {
    @Override
    public PokerHandGrouping getGroups(PokerHand hand) {
        PokerHandGrouping grouping = new PokerHandGrouping();

        List<PokerHand> combinations = new ArrayList<>();

        Map<String, List<Card>> cardTypeGrouping = PokerHandUtil.getCardTypeGrouping(hand);

        List<Card> flushCards = cardTypeGrouping.entrySet().stream().map(s -> s.getValue().get(0)).collect(Collectors.toList());

        if (flushCards.size() == 5 && FlushServiceUtil.getSuits(cardTypeGrouping).size() == 1) {
            combinations.add(new PokerHand(flushCards));
        }

        grouping.setCombination(combinations);

        return grouping;
    }

    @Override
    public boolean isCombinationPresent(PokerHand hand) {
        Map<String, List<Card>> cardTypeGrouping = PokerHandUtil.getCardTypeGrouping(hand);

        List<Card> flushCards = cardTypeGrouping.entrySet().stream().map(s -> s.getValue().get(0)).collect(Collectors.toList());

        if (flushCards.size() == 5 && FlushServiceUtil.getSuits(cardTypeGrouping).size() == 1) {
            return true;
        }
        return false;
    }

}
