package com.odsinada.icm;

import java.util.Comparator;

public enum Combination {
    FOUR_OF_A_KIND(8, new FourOfAKindTieBreaker(new FourOfAKindServiceImpl()), new FourOfAKindServiceImpl())
    , FULL_HOUSE(7, new FullHouseTieBreaker(new FullHouseServiceImpl()), new FullHouseServiceImpl())
    , THREE_OF_A_KIND(4, new ThreeOfAKindTieBreaker(new ThreeOfAKindServiceImpl()), new ThreeOfAKindServiceImpl())
    , PAIR(2, new PairCardTieBreaker(new PairServiceImpl()), new PairServiceImpl())
    , HIGH_CARD(1, new HighCardTieBreaker(), null);

    private final int rank;
    private final CombinationService combinationService;
    private Comparator tieBreaker;

    Combination(int rank, Comparator<PokerHand> tieBreaker, CombinationService combinationService) {
        this.rank = rank;
        this.tieBreaker = tieBreaker;
        this.combinationService = combinationService;
    }

    public Comparator<PokerHand> getTieBreaker() {
        return tieBreaker;
    }

    public CombinationService getCombinationService(){
        return combinationService;
    }
}
