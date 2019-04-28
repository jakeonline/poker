package com.odsinada.icm;

import java.util.Comparator;

public class HandBaseServiceBase implements HandBaseService {

    @Override
    public HandBaseServiceResult compare(HandBase hand1, HandBase hand2) {
        HandBaseServiceResult result = new HandBaseServiceResultBase();

        int compareToResult = hand1.getHighestCombination().compareTo(hand2.getHighestCombination());
        boolean isSameCombination = compareToResult == 0;
        boolean isHand1HigherThanHand2 = compareToResult == -1;
        boolean isHand1LowerThanHand2 = compareToResult == 1;

        if (isSameCombination) {
            Comparator<HandBase> tieBreaker = hand1.getHighestCombination().getTieBreaker();

            compareToResult = tieBreaker.compare(hand1, hand2);
            isHand1HigherThanHand2 = compareToResult == -1;
            isHand1LowerThanHand2 = compareToResult == 1;

            if( isHand1HigherThanHand2 ){
                result.setWinner(hand1);
                result.setLoser(hand2);
            } else {
                result.setWinner(hand2);
                result.setLoser(hand1);
            }
        }

        if (isHand1HigherThanHand2) {
            result.setWinner(hand1);
            result.setLoser(hand2);
        } else if (isHand1LowerThanHand2){
            result.setWinner(hand2);
            result.setLoser(hand1);
        }

        return result;
    }
}
