package com.odsinada.icm.fourofakind;

import com.odsinada.icm.Card;
import com.odsinada.icm.PokerHand;
import com.odsinada.icm.PokerHandGrouping;
import com.odsinada.icm.PokerHandUtil;

import java.util.*;

public class FourOfAKindServiceImpl implements FourOfAKindService {

    private static final int COUNT = 4;

    @Override
    public PokerHandGrouping getGroups(PokerHand hand) {
        PokerHandGrouping grouping = new PokerHandGrouping();

        List<PokerHand> combinations = new ArrayList<>();

        Map<String, List<Card>> cardTypeGrouping = PokerHandUtil.getCardTypeGrouping(hand);

        List<Card> fourOfAKindCards = getFourOfAKindCards(cardTypeGrouping);
        if (fourOfAKindCards != null) {
            combinations.add(new PokerHand(fourOfAKindCards));
        }

        List<Card> nonFourOfAKindCards = getNonFourOfAKindCards(cardTypeGrouping);

        grouping.setCombination(combinations);
        grouping.setNonCombination(new PokerHand(nonFourOfAKindCards));

        return grouping;
    }

    private List<Card> getNonFourOfAKindCards(Map<String, List<Card>> cardTypeGrouping) {
        List<Card> nonFourOfAKindCards = new ArrayList<>();
        cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() != COUNT).forEach(t -> nonFourOfAKindCards.addAll(t.getValue()));
        return nonFourOfAKindCards;
    }

    private List<Card> getFourOfAKindCards(Map<String, List<Card>> cardTypeGrouping) {
        List<Card> fourOfAKindCards = null;
        Optional<Map.Entry<String, List<Card>>> fourOfAKindGroup = cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() == COUNT).findFirst();

        if(fourOfAKindGroup.isPresent()){
            fourOfAKindCards = new ArrayList<>(fourOfAKindGroup.get().getValue());
        }

        return fourOfAKindCards;
    }

    @Override
    public boolean isCombinationPresent(PokerHand hand) {
        Map<String, List<Card>> cardTypeGrouping = PokerHandUtil.getCardTypeGrouping(hand);

        List<Card> fourOfAKindCards = getFourOfAKindCards(cardTypeGrouping);
        if (fourOfAKindCards != null) {
            return true;
        }
        return false;
    }
}
