package com.odsinada.icm.straightflush;

import com.odsinada.icm.PokerHand;
import com.odsinada.icm.PokerHandGrouping;

import java.util.Comparator;


public class StraightFlushTieBreaker implements Comparator<PokerHand> {

    private final StraightFlushService service;

    public StraightFlushTieBreaker(StraightFlushService service) {
        this.service = service;
    }

    @Override
    public int compare(PokerHand hand1, PokerHand hand2) {

        PokerHandGrouping hand1Groups = service.getGroups(hand1);
        PokerHandGrouping hand2Groups = service.getGroups(hand2);

        PokerHand hand1Combo = hand1Groups.getCombination().get(0);
        PokerHand hand2Combo = hand2Groups.getCombination().get(0);

        int compareResult = compareCombination(hand1Combo, hand2Combo);

        return compareResult;
    }

    private int compareCombination(PokerHand hand1, PokerHand hand2) {
        if (hand1.getHighCard().isHigher(hand2.getHighCard())) {
            return -1;
        } else if (hand2.getHighCard().isHigher(hand1.getHighCard())) {
            return 1;
        } else {
            return 0;
        }
    }
}

