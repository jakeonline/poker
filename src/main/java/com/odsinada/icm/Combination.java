package com.odsinada.icm;

import java.util.Comparator;

public enum Combination {
    FOUR_OF_A_KIND(8, new FourOfAKindTieBreaker(new FourOfAKindServiceImpl()))
    , FULL_HOUSE(7, new FullHouseTieBreaker(new FullHouseServiceImpl()))
    , THREE_OF_A_KIND(4, new ThreeOfAKindTieBreaker(new ThreeOfAKindServiceImpl()))
    , PAIR(2, new PairCardTieBreaker(new PairServiceImpl()))
    , HIGH_CARD(1, new HighCardTieBreaker());

    private final int rank;
    private Comparator tieBreaker;

    Combination(int rank, Comparator<PokerHand> tieBreaker) {
        this.rank = rank;
        this.tieBreaker = tieBreaker;
    }

    public Comparator<PokerHand> getTieBreaker() {
        return tieBreaker;
    }
}
