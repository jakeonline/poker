package com.odsinada.icm;

import java.util.ArrayList;
import java.util.List;

public class ThreeOfAKindGroups {
    private List<HandBase> combination = new ArrayList<>();
    private HandBase nonCombination = new HandBase("");


    public void setCombination(List<HandBase> combination) {
        this.combination = combination;
    }

    public List<HandBase> getCombination() {
        return combination;
    }

    public HandBase getNonCombination() {
        return nonCombination;
    }

    public void setNonCombination(HandBase nonCombination) {
        this.nonCombination = nonCombination;
    }
}
