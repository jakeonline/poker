package com.odsinada.icm;

import java.util.ArrayList;
import java.util.List;

public class ThreeOfAKindGroups {
    private List<PokerHand> combination = new ArrayList<>();
    private PokerHand nonCombination = new PokerHand("");


    public void setCombination(List<PokerHand> combination) {
        this.combination = combination;
    }

    public List<PokerHand> getCombination() {
        return combination;
    }

    public PokerHand getNonCombination() {
        return nonCombination;
    }

    public void setNonCombination(PokerHand nonCombination) {
        this.nonCombination = nonCombination;
    }
}
