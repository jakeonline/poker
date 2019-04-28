package com.odsinada.icm;

public enum Card {
    C2_D("2", 2, "D"),
    C3_D("3", 3, "D"),
    C4_D("4", 4, "D"),
    C5_D("5", 5, "D"),
    C6_D("6", 6, "D"),
    C7_D("7", 7, "D"),
    C8_D("8", 8, "D"),
    C9_D("9", 9, "D"),
    C10_D("10", 10, "D"),
    CJ_D("J", 11, "D"),
    CQ_D("Q", 12, "D"),
    CK_D("K", 13, "D"),
    CA_D("A", 14, "D"),

    C2_H("2", 2, "H"),
    C3_H("3", 3, "H"),
    C4_H("4", 4, "H"),
    C5_H("5", 5, "H"),
    C6_H("6", 6, "H"),
    C7_H("7", 7, "H"),
    C8_H("8", 8, "H"),
    C9_H("9", 9, "H"),
    C10_H("10", 10, "H"),
    CJ_H("J", 11, "H"),
    CQ_H("Q", 12, "H"),
    CK_H("K", 13, "H"),
    CA_H("A", 14, "H"),

    C2_S("2", 2, "S"),
    C3_S("3", 3, "S"),
    C4_S("4", 4, "S"),
    C5_S("5", 5, "S"),
    C6_S("6", 6, "S"),
    C7_S("7", 7, "S"),
    C8_S("8", 8, "S"),
    C9_S("9", 9, "S"),
    C10_S("10", 10, "S"),
    CJ_S("J", 11, "S"),
    CQ_S("Q", 12, "S"),
    CK_S("K", 13, "S"),
    CA_S("A", 14, "S"),

    C2_C("2", 2, "C"),
    C3_C("3", 3, "C"),
    C4_C("4", 4, "C"),
    C5_C("5", 5, "C"),
    C6_C("6", 6, "C"),
    C7_C("7", 7, "C"),
    C8_C("8", 8, "C"),
    C9_C("9", 9, "C"),
    C10_C("10", 10, "C"),
    CJ_C("J", 11, "C"),
    CQ_C("Q", 12, "C"),
    CK_C("K", 13, "C"),
    CA_C("A", 14, "C"),

    C_JOKER("X", 0, "Y");

    private String value;
    private int order;
    private String suit;


    Card(String value, int order, String suit){
        this.value = value;
        this.order = order;
        this.suit = suit;
    }

    public boolean isHigher(Card card) {
        return this.getOrder() > card.getOrder();
    }

    public static Card of(String value, String suit) {
        for (Card card : Card.values()) {
            if (card.value.equals(value) && card.suit.equals(suit)) {
                return card;
            }
        }
        return C_JOKER;
    }

    public int getOrder(){
        return this.order;
    }
}
