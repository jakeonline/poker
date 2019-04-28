package com.odsinada.icm;

import java.io.IOException;
import java.util.Scanner;
import java.util.StringJoiner;

public class PokerCLI {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        int player1Tally = 0;
        int player2Tally = 0;

        while (input.hasNext()) {

            String[] cards = input.nextLine().split(" ");

            StringJoiner hand1Joiner = new StringJoiner(" ");
            for (int i = 0; i < 5; i++) {
                hand1Joiner.add(cards[i]);
            }

            StringJoiner hand2Joiner = new StringJoiner(" ");
            for (int i = 5; i < 10; i++) {
                hand2Joiner.add(cards[i]);
            }

            HandBase hand1 = new HandBase(hand1Joiner.toString());
            HandBase hand2 = new HandBase(hand2Joiner.toString());

            Result result = hand1.versus(hand2);
            if (hand1 == result.getWinner()) {
                ++player1Tally;
            } else {
                ++player2Tally;
            }
        }

        System.out.println("Player 1: " + player1Tally);
        System.out.println("Player 2: " + player2Tally);
    }
}
