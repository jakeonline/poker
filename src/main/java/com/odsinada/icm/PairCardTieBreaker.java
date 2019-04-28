package com.odsinada.icm;

import java.util.Comparator;

public class PairCardTieBreaker implements Comparator<PokerHand> {

    private final PairService service;
    private final HighCardTieBreaker highCardTieBreaker;

    public PairCardTieBreaker(PairService service) {
        this.service = service;
        this.highCardTieBreaker = new HighCardTieBreaker();
    }

    @Override
    public int compare(PokerHand hand1, PokerHand hand2) {

        PokerHandGrouping hand1Groups = service.getGroups(hand1);
        PokerHandGrouping hand2Groups = service.getGroups(hand2);

        PokerHand hand1Pair = hand1Groups.getCombination().get(0);
        PokerHand hand2Pair = hand2Groups.getCombination().get(0);

        int comparePairResult = comparePair(hand1Pair, hand2Pair);
        if (comparePairResult == 0) {
            comparePairResult = highCardTieBreaker.compare(hand1Groups.getNonCombination(), hand2Groups.getNonCombination());
        }

        return comparePairResult;
    }

    private int comparePair(PokerHand hand1, PokerHand hand2) {
        if (hand1.getHighCard().isHigher(hand2.getHighCard())) {
            return -1;
        } else if (hand2.getHighCard().isHigher(hand1.getHighCard())) {
            return 1;
        } else {
            return 0;
        }
    }
}

