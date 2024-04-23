package com.example.application;

import java.util.ArrayList;
import java.util.Collections;

public class test {

    public static void main(String[] args) {


            ArrayList<Card> deck = new ArrayList<>();
            // deck.clear();

            ArrayList<String> suits = new ArrayList<>();
            suits.add("clubs"); suits.add("diamonds");
            suits.add("hearts"); suits.add("spades");

            ArrayList<String> values = new ArrayList<>();
            for (int i = 2; i <= 10; i++) {
                values.add(String.valueOf(i));
            }
            values.add("ace"); values.add("jack");
            values.add("queen"); values.add("king");

            // number of decks = 20 --> large so player can play nonstop
            for (int i = 0; i < 20; i++) {
                for (String suit : suits) {
                    for (String value : values) {
                        Card card = new Card(suit, value);
                        deck.add(card);
                    }
                }
            }

            // shuffle deck
            Collections.shuffle(deck);

        // System.out.println(deck.toString());

        String value = deck.get(0).getValue();
        String suit = deck.get(0).getSuit();
        String value2 = deck.get(1).getValue();
        String suit2 = deck.get(1).getSuit();
        String value3 = deck.get(2).getValue();
        String suit3 = deck.get(2).getSuit();
        String value4 = deck.get(3).getValue();
        String suit4 = deck.get(3).getSuit();

        String cardImage1 = value + "_of_" + suit + ".png";
        String cardImage2 = value2 + "_of_" + suit2 + ".png";
        String cardImage3 = value3 + "_of_" + suit3 + ".png";
        String cardImage4 = value4 + "_of_" + suit4 + ".png";

        // System.out.println(cardImage1 + " " + cardImage2 + " " + cardImage3 + " " + cardImage4);


    }
}
