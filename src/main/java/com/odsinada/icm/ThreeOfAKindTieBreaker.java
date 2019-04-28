package com.odsinada.icm;

import java.util.Comparator;

public class ThreeOfAKindTieBreaker implements Comparator<HandBase> {

    private final ThreeOfAKindService service;
    private HighCardTieBreaker highCardTieBreaker;

    public ThreeOfAKindTieBreaker(ThreeOfAKindService service){
        this.service = service;
        this.highCardTieBreaker = new HighCardTieBreaker();
    }

    @Override
    public int compare(HandBase hand1, HandBase hand2) {

        ThreeOfAKindGroups hand1Groups = service.getGroups(hand1);
        ThreeOfAKindGroups hand2Groups = service.getGroups(hand2);

        HandBase hand1Combo = hand1Groups.getCombination().get(0);
        HandBase hand2Combo = hand2Groups.getCombination().get(0);

        int compareResult = compareCombination(hand1Combo, hand2Combo);
        if (compareResult == 0) {
            compareResult = highCardTieBreaker.compare(hand1Groups.getNonCombination(), hand2Groups.getNonCombination());
        }

        return compareResult;
    }

    private int compareCombination(HandBase hand1, HandBase hand2) {
        if (hand1.getHighCard().isHigher(hand2.getHighCard())) {
            return -1;
        } else if (hand2.getHighCard().isHigher(hand1.getHighCard())) {
            return 1;
        } else {
            return 0;
        }
    }
}