package com.odsinada.icm;

import java.util.Comparator;

public class PokerServiceImpl implements PokerService {

    @Override
    public PokerResult compare(PokerHand hand1, PokerHand hand2) {
        PokerResult result = new PokerResultImpl();

        int compareToResult = hand1.getHighestCombination().compareTo(hand2.getHighestCombination());
        boolean isSameCombination = compareToResult == 0;

        if (isSameCombination) {
            Comparator<PokerHand> tieBreaker = hand1.getHighestCombination().getTieBreaker();
            setResult(hand1, hand2, result, tieBreaker.compare(hand1, hand2));
        } else {
            setResult(hand1, hand2, result, compareToResult);
        }


        return result;
    }

    private void setResult(PokerHand hand1, PokerHand hand2, PokerResult result, int compareResult){
        boolean isHand1HigherThanHand2 = compareResult == -1;
        boolean isHand1LowerThanHand2 = compareResult == 1;

        if (isHand1HigherThanHand2) {
            result.setWinner(hand1);
            result.setLoser(hand2);
        } else if (isHand1LowerThanHand2){
            result.setWinner(hand2);
            result.setLoser(hand1);
        }

    }
}
