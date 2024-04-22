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


    }
}
