package com.odsinada.icm.royal;

import com.odsinada.icm.Card;
import com.odsinada.icm.PokerHand;
import com.odsinada.icm.PokerHandGrouping;
import com.odsinada.icm.PokerHandUtil;
import com.odsinada.icm.flush.FlushServiceUtil;
import com.odsinada.icm.straight.StraightServiceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoyalFlushServiceImpl implements RoyalFlushService {

    @Override
    public PokerHandGrouping getGroups(PokerHand hand) {
        PokerHandGrouping grouping = new PokerHandGrouping();

        List<PokerHand> combinations = new ArrayList<>();

        Map<String, List<Card>> cardTypeGrouping = PokerHandUtil.getCardTypeGrouping(hand);

        List<Card> royalStraightCards = StraightServiceUtil.getRoyalCards(cardTypeGrouping);

        if (!royalStraightCards.isEmpty() && FlushServiceUtil.getSuits(cardTypeGrouping).size() == 1) {
            combinations.add(new PokerHand(royalStraightCards));
        }

        grouping.setCombination(combinations);

        return grouping;
    }

    @Override
    public boolean isCombinationPresent(PokerHand hand) {
        Map<String, List<Card>> cardTypeGrouping = PokerHandUtil.getCardTypeGrouping(hand);

        List<Card> royalStraightCards = StraightServiceUtil.getRoyalCards(cardTypeGrouping);

        if (!royalStraightCards.isEmpty() && FlushServiceUtil.getSuits(cardTypeGrouping).size() == 1) {
            return true;
        }
        return false;
    }


}
