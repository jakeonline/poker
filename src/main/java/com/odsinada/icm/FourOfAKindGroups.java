package com.odsinada.icm;

import java.util.ArrayList;
import java.util.List;

public class FourOfAKindGroups {
    private List<PokerHand> combination = new ArrayList<>();
    private PokerHand nonCombination = new PokerHand("");

    public List<PokerHand> getCombination() {
        return combination;
    }

    public void setCombination(List<PokerHand> combination) {
        this.combination = combination;
    }

    public PokerHand getNonCombination() {
        return nonCombination;
    }

    public void setNonCombination(PokerHand nonCombination) {
        this.nonCombination = nonCombination;
    }
}
