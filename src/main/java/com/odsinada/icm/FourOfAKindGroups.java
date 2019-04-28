package com.odsinada.icm;

import java.util.ArrayList;
import java.util.List;

public class FourOfAKindGroups {
    private List<HandBase> combination = new ArrayList<>();
    private HandBase nonCombination = new HandBase("");

    public List<HandBase> getCombination() {
        return combination;
    }

    public void setCombination(List<HandBase> combination) {
        this.combination = combination;
    }

    public HandBase getNonCombination() {
        return nonCombination;
    }

    public void setNonCombination(HandBase nonCombination) {
        this.nonCombination = nonCombination;
    }
}
