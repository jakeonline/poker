package com.odsinada.icm.twopairs;

import com.odsinada.icm.PokerHand;
import com.odsinada.icm.PokerHandGrouping;
import com.odsinada.icm.fullhouse.FullHouseService;
import com.odsinada.icm.highcard.HighCardTieBreaker;

import java.util.Comparator;

public class TwoPairsTieBreaker implements Comparator<PokerHand> {

    private final TwoPairsService service;
    private final HighCardTieBreaker highCardTieBreaker;

    public TwoPairsTieBreaker(TwoPairsService service) {
        this.service = service;
        this.highCardTieBreaker = new HighCardTieBreaker();
    }

    @Override
    public int compare(PokerHand hand1, PokerHand hand2) {

        PokerHandGrouping hand1Groups = service.getGroups(hand1);
        PokerHandGrouping hand2Groups = service.getGroups(hand2);

        PokerHand hand1Combo = hand1Groups.getCombination().get(0);
        PokerHand hand2Combo = hand2Groups.getCombination().get(0);

        int compareResult = compareCombination(hand1Combo, hand2Combo);
        if (compareResult == 0) {
            hand1Combo = hand1Groups.getCombination().get(1);
            hand2Combo = hand2Groups.getCombination().get(1);
            compareResult = compareCombination(hand1Combo, hand2Combo);
        }
        if (compareResult == 0) {
            compareResult = highCardTieBreaker.compare(hand1Groups.getNonCombination(), hand2Groups.getNonCombination());
        }

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
