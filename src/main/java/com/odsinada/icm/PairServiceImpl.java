package com.odsinada.icm;

import java.util.*;
import java.util.stream.Collectors;

public class PairServiceImpl implements PairService {

    @Override
    public PairGroups getGroups(HandBase hand) {
        PairGroups pairGroup = new PairGroups();

        List<HandBase> pairs = new ArrayList<>();

        Map<Integer, List<Card>> cardTypeGrouping = new HashMap<>();
        for (Card eachCard : hand.getCards()) {

            List<Card> cardList = cardTypeGrouping.get(eachCard.getOrder());
            if (cardList == null) {
                cardList = new ArrayList<>();
                cardTypeGrouping.put(eachCard.getOrder(), cardList);
            }

            cardList.add(eachCard);

        }

        Optional<Map.Entry<Integer, List<Card>>> pairCards = cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() == 2).findFirst();
        if(pairCards.isPresent()){
            pairs.add(new HandBase(pairCards.get().getValue()));
        }

        List<Card> nonPairCards = new ArrayList<>();
        cardTypeGrouping.entrySet().stream().filter(s -> s.getValue().size() != 2).forEach(t -> nonPairCards.addAll(t.getValue()));

        pairGroup.setPairs(pairs);
        pairGroup.setNonPair(new HandBase(nonPairCards));

        return pairGroup;
    }
}
