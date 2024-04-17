package com.example.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@Route("")
public class MainLayout extends VerticalLayout {
    //private static final long serialVersionUID = 1L;
    // ??? ^ FOR IMAGE BUT DOESN'T WORK
    public MainLayout() {

        // page header
        H1 header = new H1("Blackjack Simulator");
        // add header to page
        add(header);

        // Start Game Button (loads everything in, creates deck, etc)

        // TO DO ~~~~~~~~~~~~~~~~~~~~~

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

        // Game Section, Deal Cards and whatnot

        // TO DO ~~~~~~~~~~~~~~~~~~~~~~~~~~
        Image image = new Image("images/queen_of_spades.png", "Card");
        image.setWidth("100px");
        add(image);
        // Image image = new Image("images/myimage.png", "My Alt Image");
        // add(image);

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



        // create reset button???

    }
}
