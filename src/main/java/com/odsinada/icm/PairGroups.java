package com.odsinada.icm;

import java.util.ArrayList;
import java.util.List;

public class PairGroups {
    private List<HandBase> pairs = new ArrayList<>();
    private HandBase nonPair = new HandBase("");

    public void setPairs(List<HandBase> pairs) {
        this.pairs = pairs;
    }

    public void setNonPair(HandBase nonPair) {
        this.nonPair = nonPair;
    }

    public List<HandBase> getPairs() {
        return pairs;
    }

    public HandBase getNonPair() {
        return nonPair;
    }
}
