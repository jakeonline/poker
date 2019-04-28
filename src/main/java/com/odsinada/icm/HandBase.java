package com.odsinada.icm;

import java.util.*;

import static com.odsinada.icm.Card.C_JOKER;

public class HandBase implements Hand {

    private HandBaseService service;
    private String handDetail;
    private final List<Card> cards = new ArrayList<>(5);
    private Set<Combination> combinations = null;

    public HandBase(String handDetail) {
        this.handDetail = handDetail;
        if (handDetail.length() > 0) {
            for (String code : handDetail.split(" ")) {
                cards.add(Card.of(code.substring(0,1), code.substring(1,2)));
            }
        }
        this.service = new HandBaseServiceBase();
    }

    public HandBase(List<Card> cards) {
        this.cards.addAll(cards);
        this.service = new HandBaseServiceBase();
    }

    public void setService(HandBaseService service) {
        this.service = service;
    }

    @Override
    public Result versus(Hand otherHand) {
        Result result = new ResultBase();

        HandBaseServiceResult serviceResult = service.compare(this, (HandBase) otherHand);
        result.setWinner(serviceResult.getWinner());
        result.setLoser(serviceResult.getLoser());

        return result;
    }

    public Card getHighCard() {
        return getHighestCard();
    }

    private Card getHighestCard() {
        this.cards.sort((c1, c2) -> c2.getOrder() - c1.getOrder());
        if(!this.cards.isEmpty()) {
            return cards.get(0);
        }
        return C_JOKER;
    }

    public List<Card> getCards() {
        return this.cards;
    }

    @Override
    public String toString(){
        return this.handDetail;
    }

    public Set<Combination> getCombinations() {
        return getPopulatedCombinations();
    }

    /**
     * A set of combinations naturally sorted according to rank from highest to lowest (via TreeSet)
     * */
    private Set<Combination> getPopulatedCombinations() {
        if (combinations == null) {
            combinations = new TreeSet<>();

            if (!this.cards.isEmpty() && C_JOKER != getHighestCard()) {
                combinations.add(Combination.HIGH_CARD);
            }

            Map<Integer, Integer> cardTypeTally = getCardTypeTally();

            if (!this.cards.isEmpty() && C_JOKER != getHighestCard()) {
                Optional<Map.Entry<Integer, Integer>> cardTypeHighestTally = cardTypeTally.entrySet().stream()
                        .filter(s -> s.getKey() > 0)
                        .max((cardOrder1, cardOrder2) -> cardOrder1.getValue() - cardOrder2.getValue());
                Integer cardTypeTallyCount = cardTypeHighestTally.isPresent() ? cardTypeHighestTally.get().getValue() : 0;

                switch (cardTypeTallyCount) {
                    case 4: combinations.add(Combination.FOUR_OF_A_KIND);
                    case 3: combinations.add(Combination.THREE_OF_A_KIND);
                    case 2: combinations.add(Combination.PAIR);
                }
            }

            if (!this.cards.isEmpty() && C_JOKER != getHighestCard()) {
                if (cardTypeTally.size() == 2) {
                    Iterator<Map.Entry<Integer, Integer>> cardTypeTallyIter = cardTypeTally.entrySet().iterator();
                    Integer firstTypeCount = cardTypeTallyIter.next().getValue();
                    Integer secondTypeCount = cardTypeTallyIter.next().getValue();

                    List<Integer> typeCounts = Arrays.asList(firstTypeCount, secondTypeCount);

                    if (typeCounts.contains(3) && typeCounts.contains(2)) {
                        combinations.add(Combination.FULL_HOUSE);
                    }

                }

            }

            /*
            for (Map.Entry<Integer, Integer> entry : cardTypeTallyCount.entrySet()) {
                if (entry.getValue() > 1) {

                    break;
                }
            }
            */
        }
        return combinations;
    }

    private Map<Integer, Integer> getCardTypeTally() {
        Map<Integer, Integer> cardTypeTally = new HashMap<>();
        for (Card eachCard : cards) {

            Integer cardTypeCount = cardTypeTally.get(eachCard.getOrder());
            if (cardTypeCount == null) {
                cardTypeTally.put(eachCard.getOrder(), 1);
            } else {
                cardTypeTally.put(eachCard.getOrder(), ++cardTypeCount);
            }
        }
        return cardTypeTally;
    }

    public Combination getHighestCombination() {
        return getPopulatedCombinations().iterator().next();
    }
}
