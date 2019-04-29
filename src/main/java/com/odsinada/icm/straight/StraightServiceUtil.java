package com.odsinada.icm.straight;

import com.odsinada.icm.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StraightServiceUtil {
    public static List<Card> getStraightCards(Map<String, List<Card>> cardTypeGrouping) {
        return getStraightCards(cardTypeGrouping, false);
    }

    public static List<Card> getRoyalCards(Map<String, List<Card>> cardTypeGrouping) {
        return getStraightCards(cardTypeGrouping, true);
    }

    private static List<Card> getStraightCards(Map<String, List<Card>> cardTypeGrouping, boolean isRoyal) {
        List<Card> straightCards = new ArrayList<>();

        List<Card> allCards = cardTypeGrouping.entrySet().stream().map(s -> s.getValue().get(0)).collect(Collectors.toList());

        List<Integer> allCardRanks = allCards.stream().map(s -> s.getRank()).collect(Collectors.toList());

        if (allCards.size() == 5 && isStraight(allCardRanks, isRoyal)) {
            straightCards = allCards;
        }

        return straightCards;
    }

    private static boolean isStraight(List<Integer> allCardRanks, boolean isRoyal) {
        Collections.sort(allCardRanks);

        Integer previousRank = allCardRanks.get(0);
        if (isRoyal && isRoyalStartingRankNotMet(previousRank)) {
            return false;
        }

        boolean isStraight = true;
        for (int i = 1; i < 5; i++) {
            Integer currRank = allCardRanks.get(i);
            if (isNotSucceedingRank(previousRank, currRank)) {
                isStraight = false;
            }
            previousRank = currRank;
        }

        return isStraight;
    }

    private static boolean isNotSucceedingRank(Integer previousRank, Integer currRank) {
        return currRank - previousRank != 1;
    }

    /**
     * Minimum rank should be 10
     */
    private static boolean isRoyalStartingRankNotMet(Integer rank) {
        return 10 != rank;
    }

}
