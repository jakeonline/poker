package com.odsinada.icm.flush;

import com.odsinada.icm.Card;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FlushServiceUtil {

    public static Set<Card.Suit> getSuits(Map<String, List<Card>> cardTypeGrouping) {
        Set<Card.Suit> suits = new HashSet<>();
        cardTypeGrouping.entrySet().stream().map(s -> s.getValue()).forEach(t -> t.forEach( card -> suits.add(card.getSuit())));
        return suits;
    }
}
