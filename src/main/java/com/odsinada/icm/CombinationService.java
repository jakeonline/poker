package com.odsinada.icm;

public interface CombinationService {
    PokerHandGrouping getGroups(PokerHand hand);

    boolean isCombinationPresent(PokerHand hand);
}
