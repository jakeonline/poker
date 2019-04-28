package com.odsinada.icm.highcard;

import com.odsinada.icm.Card;
import com.odsinada.icm.PokerHand;

import java.util.Comparator;
import java.util.List;

public class HighCardTieBreaker implements Comparator<PokerHand> {

    @Override
    public int compare(PokerHand hand1, PokerHand hand2) {
        return recursiveCompareHighCard(hand1, hand2);
    }

    private int recursiveCompareHighCard(PokerHand hand1, PokerHand hand2) {
        int compareResult = compareHighCard(hand1, hand2);

        if(compareResult == 0){
            PokerHand nextBestHand1 = getNextBestHand(hand1);
            PokerHand nextBestHand2 = getNextBestHand(hand2);
            compareResult = recursiveCompareHighCard(nextBestHand1, nextBestHand2);
        }

        return compareResult;
    }

    private PokerHand getNextBestHand(PokerHand hand) {
        List<Card> handCards = hand.getCards();
        handCards.remove(hand.getHighCard());
        return new PokerHand(handCards);
    }

    private int compareHighCard(PokerHand hand1, PokerHand hand2) {
        if (hand1.getHighCard().isHigher(hand2.getHighCard())) {
            return -1;
        } else if (hand2.getHighCard().isHigher(hand1.getHighCard())){
            return 1;
        } else {
            if (hand1.getHighCard() == Card.C_JOKER
                    && hand2.getHighCard() == Card.C_JOKER) {
                return 1;
            }
            return 0;
        }
    }
}
