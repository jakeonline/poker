package com.odsinada.icm;

import java.util.*;

import static com.odsinada.icm.Card.C_JOKER;

public class PokerHand implements Hand {

    private static final String WHITESPACE = " ";
    private PokerService pokerService;
    private final List<Card> cards = new ArrayList<>(5);
    private Set<Combination> combinations = null;

    public PokerHand(String handDetail) {
        if (handDetail.length() > 0) {
            for (String code : handDetail.split(WHITESPACE)) {
                cards.add(Card.of(code.substring(0,1), code.substring(1,2)));
            }
        }
        this.pokerService = new PokerServiceImpl();
    }

    public PokerHand(List<Card> cards) {
        this.cards.addAll(cards);
        this.pokerService = new PokerServiceImpl();
    }

    public void setPokerService(PokerService pokerService) {
        this.pokerService = pokerService;
    }

    @Override
    public Result versus(Hand otherHand) {
        Result result = new ResultBase();

        PokerResult pokerResult = pokerService.compare(this, (PokerHand) otherHand);
        result.setWinner(pokerResult.getWinner());
        result.setLoser(pokerResult.getLoser());

        return result;
    }

    public Card getHighCard() {
        this.cards.sort((c1, c2) -> c2.getRank() - c1.getRank());

        return this.cards.isEmpty() ? C_JOKER : cards.get(0);
    }

    public List<Card> getCards() {
        return this.cards;
    }

    @Override
    public String toString(){
        return this.cards.toString();
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

            if (!this.cards.isEmpty() && C_JOKER != getHighCard()) {
                combinations.add(Combination.HIGH_CARD);
            }

            for (Combination combination : Combination.values()) {
                CombinationService combinationService = combination.getCombinationService();
                if( combinationService != null && combinationService.isCombinationPresent(this)){
                    combinations.add(combination);
                }
            }

        }
        return combinations;
    }

    public Combination getHighestCombination() {
        return getPopulatedCombinations().iterator().next();
    }
}
