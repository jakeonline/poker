package com.odsinada.icm;

public enum Card {
    C2_D("2", 2, Suit.DIAMOND),
    C3_D("3", 3, Suit.DIAMOND),
    C4_D("4", 4, Suit.DIAMOND),
    C5_D("5", 5, Suit.DIAMOND),
    C6_D("6", 6, Suit.DIAMOND),
    C7_D("7", 7, Suit.DIAMOND),
    C8_D("8", 8, Suit.DIAMOND),
    C9_D("9", 9, Suit.DIAMOND),
    C10_D("10", 10, Suit.DIAMOND),
    CJ_D("J", 11, Suit.DIAMOND),
    CQ_D("Q", 12, Suit.DIAMOND),
    CK_D("K", 13, Suit.DIAMOND),
    CA_D("A", 14, Suit.DIAMOND),

    C2_H("2", 2, Suit.HEART),
    C3_H("3", 3, Suit.HEART),
    C4_H("4", 4, Suit.HEART),
    C5_H("5", 5, Suit.HEART),
    C6_H("6", 6, Suit.HEART),
    C7_H("7", 7, Suit.HEART),
    C8_H("8", 8, Suit.HEART),
    C9_H("9", 9, Suit.HEART),
    C10_H("10", 10, Suit.HEART),
    CJ_H("J", 11, Suit.HEART),
    CQ_H("Q", 12, Suit.HEART),
    CK_H("K", 13, Suit.HEART),
    CA_H("A", 14, Suit.HEART),

    C2_S("2", 2, Suit.SPADE),
    C3_S("3", 3, Suit.SPADE),
    C4_S("4", 4, Suit.SPADE),
    C5_S("5", 5, Suit.SPADE),
    C6_S("6", 6, Suit.SPADE),
    C7_S("7", 7, Suit.SPADE),
    C8_S("8", 8, Suit.SPADE),
    C9_S("9", 9, Suit.SPADE),
    C10_S("10", 10, Suit.SPADE),
    CJ_S("J", 11, Suit.SPADE),
    CQ_S("Q", 12, Suit.SPADE),
    CK_S("K", 13, Suit.SPADE),
    CA_S("A", 14, Suit.SPADE),

    C2_C("2", 2, Suit.CLUB),
    C3_C("3", 3, Suit.CLUB),
    C4_C("4", 4, Suit.CLUB),
    C5_C("5", 5, Suit.CLUB),
    C6_C("6", 6, Suit.CLUB),
    C7_C("7", 7, Suit.CLUB),
    C8_C("8", 8, Suit.CLUB),
    C9_C("9", 9, Suit.CLUB),
    C10_C("10", 10, Suit.CLUB),
    CJ_C("J", 11, Suit.CLUB),
    CQ_C("Q", 12, Suit.CLUB),
    CK_C("K", 13, Suit.CLUB),
    CA_C("A", 14, Suit.CLUB),

    C_JOKER("X", 0, Suit.NONE);

    private String type;
    private int rank;
    private Suit suit;


    Card(String type, int rank, Suit suit){
        this.type = type;
        this.rank = rank;
        this.suit = suit;
    }

    public boolean isHigher(Card card) {
        return this.getRank() > card.getRank();
    }

    public static Card of(String type, String suit) {
        for (Card card : Card.values()) {
            if (card.type.equals(type) && card.suit.code.equals(suit)) {
                return card;
            }
        }
        return C_JOKER;
    }

    public int getRank(){
        return this.rank;
    }

    public String getType(){
        return this.type;
    }

    public Suit getSuit(){
        return this.suit;
    }

    public enum Suit {
        DIAMOND("D"),HEART("H"),CLUB("C"),SPADE("S"),NONE("Y");

        private final String code;

        Suit(String code) {
            this.code = code;
        }

        public static Suit fromCode(String code) {
            switch (code) {
                case "D": return Suit.DIAMOND;
                case "H": return Suit.DIAMOND;
                case "C": return Suit.DIAMOND;
                case "S": return Suit.DIAMOND;
                default: return Suit.NONE;
            }
        }
    }
}
