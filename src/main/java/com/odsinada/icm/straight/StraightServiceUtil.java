package com.odsinada.icm.straight;

import com.odsinada.icm.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StraightServiceUtil {
    public static List<Card> getStraightCards(Map<String, List<Card>> cardTypeGrouping) {
        List<Card> straightCards = new ArrayList<>();

        List<Card> allCards = cardTypeGrouping.entrySet().stream().map(s -> s.getValue().get(0)).collect(Collectors.toList());

        List<Integer> allCardRanks = allCards.stream().map(s -> s.getRank()).collect(Collectors.toList());

        if(allCards.size() == 5 && isStraight(allCardRanks)){
            straightCards = allCards;
        }

        return straightCards;
    }

    private static boolean isStraight(List<Integer> allCardRanks) {
        Collections.sort(allCardRanks);

        Integer previousRank = null;
        boolean isStraight = true;
        for (Integer rank : allCardRanks) {
            if (previousRank == null) {
                previousRank = rank;
            } else {
                if(rank - previousRank != 1){
                    isStraight = false;
                }
                previousRank = rank;
            }
        }
        return isStraight;
    }
}
