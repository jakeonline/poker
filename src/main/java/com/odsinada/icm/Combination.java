package com.odsinada.icm;

import java.util.Comparator;

public enum Combination {
    FOUR_OF_A_KIND(8, new HighCardTieBreaker())
    , FULL_HOUSE(7, new FullHouseTieBreaker())
    , THREE_OF_A_KIND(4, new ThreeOfAKindTieBreaker(new ThreeOfAKindServiceImpl()))
    , PAIR(2, new PairCardTieBreaker(new PairServiceImpl()))
    , HIGH_CARD(1, new HighCardTieBreaker());

    private final int rank;
    private Comparator tieBreaker;

    Combination(int rank, Comparator<HandBase> tieBreaker) {
        this.rank = rank;
        this.tieBreaker = tieBreaker;
    }

    public Comparator<HandBase> getTieBreaker() {
        return tieBreaker;
    }
}
