package com.odsinada.icm;

import java.util.Comparator;

public class FullHouseTieBreaker implements Comparator<PokerHand> {
    private final FullHouseService service;

    public FullHouseTieBreaker(FullHouseService service) {
        this.service = service;
    }

    @Override
    public int compare(PokerHand hand1, PokerHand hand2) {

        FullHouseGroups hand1Groups = service.getGroups(hand1);
        FullHouseGroups hand2Groups = service.getGroups(hand2);

        PokerHand hand1Combo = hand1Groups.getCombination().get(0);
        PokerHand hand2Combo = hand2Groups.getCombination().get(0);

        int compareResult = compareCombination(hand1Combo, hand2Combo);
        if (compareResult == 0) {
            hand1Combo = hand1Groups.getCombination().get(1);
            hand2Combo = hand2Groups.getCombination().get(1);
            compareResult = compareCombination(hand1Combo, hand2Combo);
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
