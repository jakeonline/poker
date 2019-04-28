package com.odsinada.icm;

import java.util.Comparator;

public class PairCardTieBreaker implements Comparator<HandBase> {

    private final PairService pairService;
    private final HighCardTieBreaker highCardTieBreaker;

    public PairCardTieBreaker(PairService pairService) {
        this.pairService = pairService;
        this.highCardTieBreaker = new HighCardTieBreaker();
    }

    @Override
    public int compare(HandBase hand1, HandBase hand2) {

        PairGroups hand1Group = pairService.getGroups(hand1);
        PairGroups hand2Group = pairService.getGroups(hand2);

        HandBase hand1Pair = hand1Group.getPairs().get(0);
        HandBase hand2Pair = hand2Group.getPairs().get(0);
        int comparePairResult = comparePair(hand1Pair, hand2Pair);
        if (comparePairResult == 0) {
            comparePairResult = highCardTieBreaker.compare(hand1Group.getNonPair(), hand2Group.getNonPair());
        }

        return comparePairResult;
    }

    private int comparePair(HandBase hand1, HandBase hand2) {
        if (hand1.getHighCard().isHigher(hand2.getHighCard())) {
            return -1;
        } else if (hand2.getHighCard().isHigher(hand1.getHighCard())) {
            return 1;
        } else {
            return 0;
        }
    }
}

