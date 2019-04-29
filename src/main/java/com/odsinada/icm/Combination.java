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
    ROYAL_FLUSH(null, ROYAL_STRAIGHT_FLUSH_SERVICE)
    , STRAIGHT_FLUSH(new StraightFlushTieBreaker(STRAIGHT_FLUSH_SERVICE), STRAIGHT_FLUSH_SERVICE)
    , FOUR_OF_A_KIND(new FourOfAKindTieBreaker(FOUR_OF_A_KIND_SERVICE), FOUR_OF_A_KIND_SERVICE)
    , FULL_HOUSE(new FullHouseTieBreaker(FULL_HOUSE_SERVICE), FULL_HOUSE_SERVICE)
    , FLUSH(new FlushTieBreaker(FLUSH_SERVICE), FLUSH_SERVICE)
    , STRAIGHT(new StraightTieBreaker(STRAIGHT_SERVICE), STRAIGHT_SERVICE)
    , THREE_OF_A_KIND(new ThreeOfAKindTieBreaker(THREE_OF_A_KIND_SERVICE), THREE_OF_A_KIND_SERVICE)
    , TWO_PAIRS(new TwoPairsTieBreaker(TWO_PAIRS_SERVICE), TWO_PAIRS_SERVICE)
    , PAIR(new PairCardTieBreaker(PAIR_SERVICE), PAIR_SERVICE)
    , HIGH_CARD(new HighCardTieBreaker(), null);

    private final CombinationService combinationService;
    private Comparator tieBreaker;

    Combination(Comparator<PokerHand> tieBreaker, CombinationService combinationService) {
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
