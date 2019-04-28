package com.odsinada.icm;

import java.util.Comparator;
import java.util.List;

public class HighCardTieBreaker implements Comparator<HandBase> {

    @Override
    public int compare(HandBase hand1, HandBase hand2) {
        return compareHighCardLoop(hand1, hand2);
    }

    private int compareHighCardLoop(HandBase hand1, HandBase hand2) {
        int compareResult = compareHighCard(hand1, hand2);

        if(compareResult == 0){
            HandBase nextBestHand1 = getNextBestHand(hand1);
            HandBase nextBestHand2 = getNextBestHand(hand2);
            compareResult = compareHighCardLoop(nextBestHand1, nextBestHand2);
        }

        return compareResult;
    }

    private HandBase getNextBestHand(HandBase hand) {
        List<Card> handCards = hand.getCards();
        handCards.remove(hand.getHighCard());
        return new HandBase(handCards);
    }

    private int compareHighCard(HandBase hand1, HandBase hand2) {
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
