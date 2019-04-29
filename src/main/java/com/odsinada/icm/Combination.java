package com.odsinada.icm;

import com.odsinada.icm.flush.FlushServiceImpl;
import com.odsinada.icm.flush.FlushTieBreaker;
import com.odsinada.icm.fourofakind.FourOfAKindServiceImpl;
import com.odsinada.icm.fourofakind.FourOfAKindTieBreaker;
import com.odsinada.icm.fullhouse.FullHouseServiceImpl;
import com.odsinada.icm.fullhouse.FullHouseTieBreaker;
import com.odsinada.icm.highcard.HighCardTieBreaker;
import com.odsinada.icm.pair.PairCardTieBreaker;
import com.odsinada.icm.pair.PairServiceImpl;
import com.odsinada.icm.straight.StraightServiceImpl;
import com.odsinada.icm.straight.StraightTieBreaker;
import com.odsinada.icm.straightflush.StraightFlushServiceImpl;
import com.odsinada.icm.straightflush.StraightFlushTieBreaker;
import com.odsinada.icm.threeofakind.ThreeOfAKindServiceImpl;
import com.odsinada.icm.threeofakind.ThreeOfAKindTieBreaker;
import com.odsinada.icm.twopairs.TwoPairsServiceImpl;
import com.odsinada.icm.twopairs.TwoPairsTieBreaker;

import java.util.Comparator;

public enum Combination {
    STRAIGHT_FLUSH(9, new StraightFlushTieBreaker(new StraightFlushServiceImpl()), new StraightFlushServiceImpl())
    , FOUR_OF_A_KIND(8, new FourOfAKindTieBreaker(new FourOfAKindServiceImpl()), new FourOfAKindServiceImpl())
    , FULL_HOUSE(7, new FullHouseTieBreaker(new FullHouseServiceImpl()), new FullHouseServiceImpl())
    , FLUSH(6, new FlushTieBreaker(new FlushServiceImpl()), new FlushServiceImpl())
    , STRAIGHT(5, new StraightTieBreaker(new StraightServiceImpl()), new StraightServiceImpl())
    , THREE_OF_A_KIND(4, new ThreeOfAKindTieBreaker(new ThreeOfAKindServiceImpl()), new ThreeOfAKindServiceImpl())
    , TWO_PAIRS(3, new TwoPairsTieBreaker(new TwoPairsServiceImpl()), new TwoPairsServiceImpl())
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
