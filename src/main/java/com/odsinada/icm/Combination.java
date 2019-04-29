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
import com.odsinada.icm.royal.RoyalFlushServiceImpl;
import com.odsinada.icm.straight.StraightServiceImpl;
import com.odsinada.icm.straight.StraightTieBreaker;
import com.odsinada.icm.straightflush.StraightFlushServiceImpl;
import com.odsinada.icm.straightflush.StraightFlushTieBreaker;
import com.odsinada.icm.threeofakind.ThreeOfAKindServiceImpl;
import com.odsinada.icm.threeofakind.ThreeOfAKindTieBreaker;
import com.odsinada.icm.twopairs.TwoPairsServiceImpl;
import com.odsinada.icm.twopairs.TwoPairsTieBreaker;

import java.util.Comparator;

import static com.odsinada.icm.Combination.Service.*;

public enum Combination {
    ROYAL_FLUSH(10, null, ROYAL_STRAIGHT_FLUSH_SERVICE)
    , STRAIGHT_FLUSH(9, new StraightFlushTieBreaker(STRAIGHT_FLUSH_SERVICE), STRAIGHT_FLUSH_SERVICE)
    , FOUR_OF_A_KIND(8, new FourOfAKindTieBreaker(FOUR_OF_A_KIND_SERVICE), FOUR_OF_A_KIND_SERVICE)
    , FULL_HOUSE(7, new FullHouseTieBreaker(FULL_HOUSE_SERVICE), FULL_HOUSE_SERVICE)
    , FLUSH(6, new FlushTieBreaker(FLUSH_SERVICE), FLUSH_SERVICE)
    , STRAIGHT(5, new StraightTieBreaker(STRAIGHT_SERVICE), STRAIGHT_SERVICE)
    , THREE_OF_A_KIND(4, new ThreeOfAKindTieBreaker(THREE_OF_A_KIND_SERVICE), THREE_OF_A_KIND_SERVICE)
    , TWO_PAIRS(3, new TwoPairsTieBreaker(TWO_PAIRS_SERVICE), TWO_PAIRS_SERVICE)
    , PAIR(2, new PairCardTieBreaker(PAIR_SERVICE), PAIR_SERVICE)
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

    static class Service {
        public static final RoyalFlushServiceImpl ROYAL_STRAIGHT_FLUSH_SERVICE = new RoyalFlushServiceImpl();
        public static final StraightFlushServiceImpl STRAIGHT_FLUSH_SERVICE = new StraightFlushServiceImpl();
        public static final FourOfAKindServiceImpl FOUR_OF_A_KIND_SERVICE = new FourOfAKindServiceImpl();
        public static final FullHouseServiceImpl FULL_HOUSE_SERVICE = new FullHouseServiceImpl();
        public static final FlushServiceImpl FLUSH_SERVICE = new FlushServiceImpl();
        public static final StraightServiceImpl STRAIGHT_SERVICE = new StraightServiceImpl();
        public static final ThreeOfAKindServiceImpl THREE_OF_A_KIND_SERVICE = new ThreeOfAKindServiceImpl();
        public static final TwoPairsServiceImpl TWO_PAIRS_SERVICE = new TwoPairsServiceImpl();
        public static final PairServiceImpl PAIR_SERVICE = new PairServiceImpl();
    }
}
