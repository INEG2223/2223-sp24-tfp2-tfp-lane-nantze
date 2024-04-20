package com.example.application.views;

import com.example.application.Card;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Route("")
public class MainLayout extends VerticalLayout {

    private ArrayList<Card> deck;

    public MainLayout() {

        // page header
        H1 header = new H1("Blackjack Simulator");
        // add header to page
        add(header);

        // Start Game Button (loads everything in, creates deck, etc)

        // TO DO ~~~~~~~~~~~~~~~~~~~~~

        // add Start Game button
        Button startGameButton = new Button("Start Game");
        add(startGameButton);

        // clickListener for startGameButton
        startGameButton.addClickListener(event -> {

            // generates decks
            deckGenerator();

        });

        // input for bet amount (unit size)
        // sizes: $25, $50, $75, $100, $150, $200, $250, $300

        List<Integer> betAmts = new ArrayList<>();
        betAmts.add(25); betAmts.add(50); betAmts.add(75);
        betAmts.add(100); betAmts.add(150); betAmts.add(200);
        betAmts.add(250); betAmts.add(300);

        ComboBox<Integer> betAmtDropdown = new ComboBox<>("Bet Amount $");
        betAmtDropdown.setItems(betAmts);

        // HorizontalLayout to add Bet Amount to
        HorizontalLayout hL1 = new HorizontalLayout();
        hL1.add(betAmtDropdown);
        add(hL1);

        // Game Section, Dealt Cards and whatnot

        // TO DO ~~~~~~~~~~~~~~~~~~~~~~~~~~

        // HorizontalLayout for cards dealt
        // HOW TO MAKE 2 HORIZONTAL LAYOUTS SIDE BY SIDE FOR DEALER THEN OUT HANDS
        HorizontalLayout playerHandDealt = new HorizontalLayout();
        playerHandDealt.setWidthFull();
        HorizontalLayout dealerHandDealt = new HorizontalLayout();
        dealerHandDealt.setWidthFull();
        HorizontalLayout bothHandsDealt = new HorizontalLayout(playerHandDealt, dealerHandDealt);
        bothHandsDealt.setWidthFull();
        add(bothHandsDealt);

        // add card images
        /*
        Image image = new Image("images/queen_of_spades.png", "Card");
        image.setWidth("100px");
        playerHandDealt.add(image);
        Image image3 = new Image("images/king_of_hearts.png", "Card");
        image3.setWidth("100px");
        image3.getStyle().set("margin-left", "-90px");
        playerHandDealt.add(image3);

        Image image2 = new Image("images/2_of_spades.png", "Card");
        image2.setWidth("100px");
        dealerHandDealt.add(image2);

         */

        // Recommendation, Win Probability, Bet Size Projection

        // TO DO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // could add this to hL1? to the right of bet size?

        // Bet Button, Confirm Bet Button (starts deal), Hit, Stand, Double, and Split Buttons

        // create Buttons
        // adds and shows bet size somewhere
        Button betButton = new Button("Bet");
        // starts dealing
        Button confirmBetButton = new Button("Confirm Bet");
        // hit
        Button hitButton = new Button("Hit");
        // stand
        Button standButton = new Button("Stand");
        // double
        Button doubleButton = new Button("Double");
        // split
        Button splitButton = new Button("Split");

        // HorizontalLayout for bet Buttons
        HorizontalLayout hL2 = new HorizontalLayout();
        hL2.add(betButton, confirmBetButton);
        // MAKE TO WHERE hL2 or hL3 ONLY SHOWS US
        add(hL2);

        // HorizontalLayout for action Buttons
        HorizontalLayout hL3 = new HorizontalLayout();
        hL3.add(hitButton, standButton, doubleButton, splitButton);
        // MAKE TO WHERE hL2 or hL3 ONLY SHOWS US
        add(hL3);

        // Deal initial hands
        confirmBetButton.addClickListener(event -> {

            String value = deck.get(0).getValue();
            String suit = deck.get(0).getSuit();

            // FIGURE OUT HOW TO ADD CARDS

        });


        // create reset button???

    }

    private void deckGenerator() {

        deck.clear();

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
    }

}
