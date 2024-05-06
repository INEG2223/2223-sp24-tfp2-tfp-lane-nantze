package com.example.application.views;

import com.example.application.Card;
import com.example.application.InitialPredictionOutcome;
import com.example.application.Suggestion;
import com.example.application.expectedBet;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.Route;
import smile.classification.RandomForest;
import tech.tablesaw.api.Table;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Creates a viewer-friendly user interface that allows for a blackjack game to be played
 */
@Route("")
public class MainLayout extends VerticalLayout {

    /**
     * A deck of cards
     */
    private ArrayList<Card> deck = new ArrayList<>();
    /**
     * The run count
     */
    private int runCount;
    /**
     * The true count
     */
    private int trueCount;
    /**
     * The numerical sum of the values in the hand
     */
    private int handSum;
    /**
     * The dealer's shown card
     */
    private int dealerUpCard;
    /**
     * Formats the dealer's hand horizontally
     */
    private HorizontalLayout dealerHandDealt = new HorizontalLayout();
    /**
     * Formats the player's hand horizontally
     */
    private HorizontalLayout playerHandDealt = new HorizontalLayout();
    /**
     * The dealer's numerical sum of the hand
     */
    private int dealerHandSum;
    /**
     * An image for the back of a card
     */
    private Image image4 = new Image("images/back_of_card.png", "Card");
    /**
     * Creates an image object to be used
     */
    private Image image1 = new Image();
    /**
     * Creates an image object to be used
     */
    private Image image2 = new Image();
    /**
     * Creates an image object to be used
     */
    private Image image3 = new Image();
    /**
     * A "True/False" value if the player's card are alike
     */
    private boolean sameCard;
    /**
     * The numerical sum of the player's split (second) hand
     */
    private int splitHandSum;
    /**
     * Creates a value that can be used to store card values
     */
    private String value3;
    /**
     * Formats the dealer's hand sum horizontally
     */
    private HorizontalLayout dealerHandSumEntry = new HorizontalLayout();
    /**
     * The player's bank balance
     */
    private double playerBalance;
    /**
     * The outcome of the hand (win, loss, or push)
     */
    private String handOutcome;
    /**
     * Used for formatting horizontally
     */
    private HorizontalLayout hL1 = new HorizontalLayout();
    /**
     * Formatting the player's bank balance horizontally
     */
    private H4 playerBalanceText = new H4();
    /**
     * Formatting the run count horizontally
     */
    private H4 runCountText = new H4();
    /**
     * Formatting the true count horizontally
     */
    private H4 trueCountText = new H4();
    /**
     * A "True/False" decision if blackjack has been dealt
     */
    private boolean BJ;
    /**
     * The amount of the current hand's bet
     */
    private int betAmount;
    /**
     * A "True/False" decision if the player double downs
     */
    private boolean doubleOrNot;
    /**
     * A "True/False" decision if the player splits
     */
    private boolean splitOrNot;
    /**
     * A "True/False" decision if the player splits and blackjack is dealt
     */
    private boolean splitBJ;
    /**
     * A "True/False" decision if the player splits and double downs
     */
    private boolean splitDoubleOrNot;
    /**
     * The number of aces the player's hand has
     */
    private int numPlayerAces;
    /**
     * The number of aces the dealer's hand has
     */
    private int numDealerAces;
    /**
     * The number of aces the player's split hand has
     */
    private int numSplitAces;
    /**
     * Formats the counts horizontally
     */
    private HorizontalLayout hLCountTexts = new HorizontalLayout();
    /**
     * Formats the player's bank balance horizontally
     */
    private HorizontalLayout hLPlayerBalance = new HorizontalLayout();
    /**
     * The number of hands the user wishes to simulate
     */
    private int numHands;
    /**
     * The table of data wishing to be used
     */
    private Table data;
    /**
     * The RandomForest being used to give predictions and suggestions
     */
    private RandomForest RF1;
    /**
     * The table of data that contains the simulated blackjack hands
     */
    private Table simulatedData;
    /**
     * The table of data that contains the simulated data using the recommended bet size
     */
    private Table simulatedBetData;
    /**
     * Formatting horizontally for predictions
     */
    private HorizontalLayout HLforSmileStuff = new HorizontalLayout();
    /**
     * Formatting horizontally for split prediction
     */
    private HorizontalLayout HLforSplitSmileStuff = new HorizontalLayout();
    /**
     * Formatting horizontally for predictions
     */
    private HorizontalLayout HLforSuggestion = new HorizontalLayout();
    /**
     * Formatting horizontally for split prediction
     */
    private HorizontalLayout HLforSplitSuggestion = new HorizontalLayout();
    /**
     * Text field for the suggestion to display
     */
    private H4 suggestionText = new H4();
    /**
     * Text field for the split suggestion to display
     */
    private H5 splitSuggestionText = new H5();
    /**
     * Formatting the expected bank balance horizontally
     */
    private HorizontalLayout HLforExpectedBet = new HorizontalLayout();
    /**
     * Text field for the expected bank balance to display
     */
    private H4 expectedBetText = new H4();
    /**
     * Formatting the predicted outcome horizontally
     */
    private HorizontalLayout HLforOutcomePrediction = new HorizontalLayout();
    /**
     * Text field for the predicted outcome to display
     */
    private H4 outcomePredictionText = new H4();
    /**
     * Formatting the split hand's predicted outcome horizontally
     */
    private HorizontalLayout HLforSplitOutcomePrediction = new HorizontalLayout();
    /**
     * Text field for the split hand's predicted outcome to display
     */
    private H5 splitOutcomePredictionText = new H5();
    /**
     * The suggested action
     */
    private String suggestionValue;
    /**
     * The predicted outcome for the hand
     */
    private String predictionValue;
    /**
     * The suggested action for the split hand
     */
    private String splitSuggestionValue;
    /**
     * The prediction outcome for the split hand
     */
    private String splitPredictionValue;
    /**
     * Formatting the split hand's cards horizontally
     */
    private HorizontalLayout splitHandDealt = new HorizontalLayout();
    /**
     * Text field for the recommended bet multiplier to be displayed in
     */
    private H4 unitMultiplierText = new H4();
    /**
     * Text field for the recommended bet to be displayed in
     */
    private H4 recommendedBetText = new H4();
    /**
     * Text field for the expected bank balance to be displayed in
     */
    private H4 expectedBankrollText = new H4();

    /*
    TO DO:
    - figure out how to limit bet amt to 1-300
    - dealer hits on soft 17
    - when split hand busts but original stays, make to where dealerHits() executes
     */

    /**
     * Formatting of the user interface
     */
    public MainLayout() {

        //Setting colors/themes
            /* Cite/Motivation to exploring to find .setBackgroundColor
                 https://stackoverflow.com/questions/54372971/vaadin-flow-textfield-how-dynamically-change-font-color

               Button Coloring Cite (Vaadin Docs)
                 https://vaadin.com/docs/latest/components/button#miscellaneous-style-variants
             */

        getElement().getStyle().setBackgroundColor("#234F1E");


        // page header
        H1 header = new H1("Blackjack Simulator");
        // add header to page
        add(header);

        // Start Game Button (loads everything in, creates deck, etc)

        // add Start Game button
        Button startGameButton = new Button("Start Game");
        startGameButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);

        // add Start Game button to HorizontalLayout
        HorizontalLayout HLwithStartButton = new HorizontalLayout();
        HLwithStartButton.setWidthFull();
        HLwithStartButton.add(startGameButton);
        add(HLwithStartButton);

        // add estimated wait time message HorizontalLayout next to start button
        HorizontalLayout HLforWaitTime = new HorizontalLayout();
        HLwithStartButton.add(HLforWaitTime);

        // add SMILE stuff to HorizontalLayout next to start button
        HLwithStartButton.add(HLforSmileStuff);
        HLforSmileStuff.setWidthFull();

        // HorizontalLayout for 3 smile things added to HLforSmileStuff
        HLforSmileStuff.add(HLforSuggestion, HLforOutcomePrediction, HLforExpectedBet);

        // set sizes of HorizontalLayouts for smile stuff
        HLforSuggestion.setWidth("20%");
        HLforOutcomePrediction.setWidth("30%");

        // player's balance (changes after every hand)
        // start at $0
        playerBalanceText = new H4(String.format("Balance: $%.2f", playerBalance));
        // HorizontalLayout for playerBalanceText
        hLPlayerBalance.add(playerBalanceText);

        /*
        // input for bet amount (unit size)
        // sizes: $10, $15, $20, $25, $50, $75, $100, $150, $200, $250, $300

        List<Integer> betAmts = new ArrayList<>();
        betAmts.add(10); betAmts.add(15); betAmts.add(20);
        betAmts.add(25); betAmts.add(50); betAmts.add(75);
        betAmts.add(100); betAmts.add(150); betAmts.add(200);
        betAmts.add(250); betAmts.add(300);

        ComboBox<Integer> betAmtDropdown = new ComboBox<>("Bet Amount $");
        betAmtDropdown.setItems(betAmts);

         */

        // input for bet amount (unit size) --> any value
        IntegerField betAmtDropdown = new IntegerField("Bet Amount $", "Enter an Integer 1-300");
        // min bet: $1, max bet: $300
        betAmtDropdown.setMin(1); betAmtDropdown.setMax(300);

        // HorizontalLayout to add Bet Amount to
        hL1.add(betAmtDropdown);
        add(hL1);

        // add text for counts next to balance in HorizontalLayout
        runCountText = new H4("Run Count: " + runCount);
        trueCountText = new H4("True Count: " + trueCount);
        hLCountTexts.add(runCountText, trueCountText);

        // estimated wait time message
        H4 estimatedWaitTimeText = new H4("Estimated Time to Load: 1 min");
        HLforWaitTime.add(estimatedWaitTimeText);

        // clickListener for startGameButton
        startGameButton.addClickListener(event -> {

            // generates decks
            deckGenerator();

            // add balance and count texts at top
            hL1.add(hLPlayerBalance, hLCountTexts);

            // initialize handSum, dealerUpCard, count values, etc
            handSum = 0; dealerUpCard = 0; trueCount = 0; runCount = 0;
            dealerHandSum = 0; sameCard = false; splitHandSum = 0;
            playerBalance = 0.00; handOutcome = "push"; BJ = false;
            betAmount = 0; doubleOrNot = false; splitOrNot = false;
            splitBJ = false; splitDoubleOrNot = false; numPlayerAces = 0;
            numDealerAces = 0; numSplitAces = 0; numHands = 0; suggestionValue = "";
            predictionValue = ""; splitSuggestionValue = ""; splitPredictionValue = "";

            // process data for RandomForest
            data = Suggestion.dataProcessing();
            // create RandomForest model
            RF1 = Suggestion.randomForest(data);

            // process data for InitialPredictionOutcome
            simulatedData = InitialPredictionOutcome.readData();

            // process data for expectedBet
            simulatedBetData = expectedBet.readEData();

            // add text to HLforSuggestion to show suggestion
            HLforSuggestion.removeAll();
            suggestionText = new H4(String.format("Suggested Action: %s", suggestionValue));
            HLforSuggestion.add(suggestionText);

            // add text to HLforOutcomePrediction to show outcome prediction
            HLforOutcomePrediction.removeAll();
            outcomePredictionText = new H4(String.format("Initial Hand Outcome Prediction: %s", predictionValue));
            HLforOutcomePrediction.add(outcomePredictionText);

            // remove estimated wait time message
            HLwithStartButton.remove(HLforWaitTime);

        });

        // Game Section, Dealt Cards and whatnot

        // Vertical Layouts to Pair HandSums with Dealt Cards
        VerticalLayout playerHandDealtWithHandSum = new VerticalLayout();
        VerticalLayout dealerHandDealtWithHandSum = new VerticalLayout();

        // HorizontalLayouts to display hand sums
        HorizontalLayout playerHandSumEntry = new HorizontalLayout();
        playerHandDealtWithHandSum.add(playerHandSumEntry);
        // HorizontalLayout dealerHandSumEntry = new HorizontalLayout();
        dealerHandDealtWithHandSum.add(dealerHandSumEntry);

        // HorizontalLayout for cards dealt
        playerHandDealt.setWidthFull();
        //HorizontalLayout dealerHandDealt = new HorizontalLayout();
        dealerHandDealt.setWidthFull();
        playerHandDealtWithHandSum.add(playerHandDealt);
        dealerHandDealtWithHandSum.add(dealerHandDealt);
        HorizontalLayout bothHandsDealt = new HorizontalLayout(playerHandDealtWithHandSum, dealerHandDealtWithHandSum);
        bothHandsDealt.setWidthFull();
        add(bothHandsDealt);

        // VerticalLayout to hold split hand and split hand sum value
        VerticalLayout splitHandDealtWithHandSum = new VerticalLayout();
        splitHandDealtWithHandSum.setWidthFull();
        // HorizontalLayout to display action suggestion and outcome prediction
        // add SMILE stuff to VerticalLayout above handTotal
        splitHandDealtWithHandSum.add(HLforSplitSmileStuff);
        HLforSplitSmileStuff.setWidthFull();
        // HorizontalLayout for 2 smile things added to HLforSplitSmileStuff
        HLforSplitSmileStuff.add(HLforSplitSuggestion, HLforSplitOutcomePrediction);
        // set sizes of HorizontalLayouts for smile stuff
        HLforSplitSuggestion.setWidth("20%");
        HLforSplitOutcomePrediction.setWidth("30%");
        // HorizontalLayout to display split hand sum
        HorizontalLayout splitHandSumEntry = new HorizontalLayout();
        splitHandDealtWithHandSum.add(splitHandSumEntry);
        // HorizontalLayout for second hand for Split
        splitHandDealtWithHandSum.add(splitHandDealt);
        add(splitHandDealtWithHandSum);
        if (sameCard) {
            // add text to layout above split card dealt to show hand sum
            H5 splitSumText = new H5("Split Hand Sum: " + splitHandSum);
            splitHandSumEntry.add(splitSumText);
        }

        // add text to layout above cards dealt to show hand sum
        H5 handSumText = new H5("Player Hand Sum: " + handSum);
        H5 dealerHandSumText = new H5("Dealer Hand Sum: " + dealerHandSum);
        playerHandSumEntry.add(handSumText);
        dealerHandSumEntry.add(dealerHandSumText);

        // Recommendation, Win Probability, Bet Size Projection

        // TO DO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // could add this to hL1? to the right of bet size?

        // Bet Button, Confirm Bet Button (starts deal), Hit, Stand, Double, and Split Buttons

        // create Buttons
        // adds and shows bet size somewhere
        // Button betButton = new Button("Bet");
        // starts dealing
        Button confirmBetButton = new Button("Confirm Bet");
        confirmBetButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        // hit
        Button hitButton = new Button("Hit");
        hitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        // stand
        Button standButton = new Button("Stand");
        standButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        // double
        Button doubleButton = new Button("Double");
        doubleButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        // split
        Button splitButton = new Button("Split");
        splitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);

        // HorizontalLayout for bet Buttons
        HorizontalLayout hL2 = new HorizontalLayout();
        hL2.add(confirmBetButton);
        add(hL2);

        // HorizontalLayout for action Buttons
        HorizontalLayout hL3 = new HorizontalLayout();
        hL3.add(hitButton, standButton, doubleButton, splitButton);
        add(hL3);

        // HorizontalLayout for split action Buttons
        HorizontalLayout splitButtonsLayout = new HorizontalLayout();
        add(splitButtonsLayout);

        // Deal initial hands
        confirmBetButton.addClickListener(event -> {

            // confirm bet amount
            betAmount = betAmtDropdown.getValue();

            // deal cards
            String value = deck.get(0).getValue();
            String suit = deck.get(0).getSuit();
            String value2 = deck.get(1).getValue();
            String suit2 = deck.get(1).getSuit();
            value3 = deck.get(2).getValue();
            String suit3 = deck.get(2).getSuit();
            // String value4 = deck.get(3).getValue();
            // String suit4 = deck.get(3).getSuit();

            String cardImage1 = "images/" + value + "_of_" + suit + ".png";
            String cardImage2 = "images/" + value2 + "_of_" + suit2 + ".png";
            String cardImage3 = "images/" + value3 + "_of_" + suit3 + ".png";
            // String cardImage4 = "images/" + value4 + "_of_" + suit4 + ".png";

            // checks if cards are the same value so they can be split
            if (value.equals(value3)) {
                sameCard = true;
            }

            // give first card to playerHandDealt
            image1 = new Image(cardImage1, "Card");
            image1.setWidth("100px");
            playerHandDealt.add(image1);

            // give first card to dealerHandDealt
            image2 = new Image(cardImage2, "Card");
            image2.setWidth("100px");
            dealerHandDealt.add(image2);

            // give second card to playerHandDealt
            image3 = new Image(cardImage3, "Card");
            image3.setWidth("100px");
            image3.getStyle().set("margin-left", "-80px");
            playerHandDealt.add(image3);

            // give second card to dealerHandDealt face down
            /*
            Image image4 = new Image(cardImage4, "Card");
            image4.setWidth("100px");
            image4.getStyle().set("margin-left", "-80px");
            */
            // we will actually give dealer's second card after player's actions
            image4.setWidth("95px");
            image4.getStyle().set("margin-left", "-80px");
            dealerHandDealt.add(image4);

            // remove 3 cards from deck
            deck.remove(0);
            deck.remove(0);
            deck.remove(0);

            // update dealerUpCard value and dealerHandSum
            if (value2.equals("jack") || value2.equals("queen") || value2.equals("king")) {
                dealerUpCard = 10;
                dealerHandSum = dealerHandSum + 10;
            }
            else if (value2.equals("ace")) {
                dealerUpCard = 11;
                dealerHandSum = dealerHandSum + 11;
            }
            else {
                dealerUpCard = Integer.parseInt(value2);
                dealerHandSum = dealerHandSum + Integer.parseInt(value2);
            }

            dealerHandSumEntry.removeAll();
            H5 dealerHandSumTextNew = new H5("Dealer Hand Sum: " + dealerHandSum);
            dealerHandSumEntry.add(dealerHandSumTextNew);

            // update handSum value

            // first card
            if (value.equals("jack") || value.equals("queen") || value.equals("king")) {
                handSum = handSum + 10;
            }
            else if (value.equals("ace")) {
                handSum = handSum + 11;
            }
            else {
                handSum = handSum + Integer.parseInt(value);
            }
            // second card
            if (value3.equals("jack") || value3.equals("queen") || value3.equals("king")) {
                handSum = handSum + 10;
            }
            else if (value3.equals("ace")) {
                handSum = handSum + 11;
            }
            else {
                handSum = handSum + Integer.parseInt(value3);
            }

            playerHandSumEntry.removeAll();
            H5 handSumTextNew = new H5("Player Hand Sum: " + handSum);
            playerHandSumEntry.add(handSumTextNew);

            // update counts
            ArrayList<String> values = new ArrayList<>();
            values.add(value); values.add(value2); values.add(value3);

            for (int i = 0; i < values.size(); i ++) {
                if (values.get(i).equals("10") || values.get(i).equals("jack") || values.get(i).equals("queen")
                        || values.get(i).equals("king") || values.get(i).equals("ace")) {
                    runCount--;
                    trueCount = runCount / (deck.size() / 52);
                }
                else if (values.get(i).equals("2") || values.get(i).equals("3") || values.get(i).equals("4")
                        || values.get(i).equals("5") || values.get(i).equals("6")) {
                    runCount++;
                    trueCount = runCount / (deck.size() / 52);
                }
                else {
                    trueCount = runCount / (deck.size() / 52);
                }
            }

            // update counts text
            hLCountTexts.remove(runCountText, trueCountText);
            runCountText = new H4("Run Count: " + runCount);
            trueCountText = new H4("True Count: " + trueCount);
            hLCountTexts.add(runCountText, trueCountText);

            // check if there are aces
            if (value.equals("ace")) {
                numPlayerAces = numPlayerAces + 1;
            }
            if (value2.equals("ace")) {
                numDealerAces = numDealerAces + 1;
            }
            if (value3.equals("ace")) {
                numPlayerAces = numPlayerAces + 1;
            }

            // check BJ
            if (handSum == 21) {
                BJ = true;

                // tells player to press next hand
                HLforSuggestion.removeAll();
                suggestionText = new H4(String.format("Suggested Action: %s", "Next Hand"));
                HLforSuggestion.add(suggestionText);

                playerBlackJack();
            }

            // updates text to HLforSuggestion to show suggestion
            if (!BJ) {
                giveSuggestion();
                HLforSuggestion.removeAll();
                suggestionText = new H4(String.format("Suggested Action: %s", suggestionValue));
                HLforSuggestion.add(suggestionText);
            }

            // updates text to HLforOutcomePrediction to show predicted outcome
            giveOutcomePrediction();
            HLforOutcomePrediction.removeAll();
            outcomePredictionText = new H4(String.format("Initial Hand Outcome Prediction: %s", predictionValue));
            HLforOutcomePrediction.add(outcomePredictionText);

            // count code below
            /*
            if (value.equals("10") || value.equals("jack") || value.equals("queen") || value.equals("king")
                    || value.equals("ace")) {
                runCount--;
                trueCount = runCount / (deck.size() / 52);
            }
            else if (value.equals("2") || value.equals("3") || value.equals("4") || value.equals("5")
                    || value.equals("6")) {
                runCount++;
                trueCount = runCount / (deck.size() / 52);
            }
            else {
                trueCount = runCount / (deck.size() / 52);
            }
             */
        });

        // hitButton clickListener
        hitButton.addClickListener(event -> {

            // add card to playerHandDealt
            String value = deck.get(0).getValue();
            String suit = deck.get(0).getSuit();

            String cardImage = "images/" + value + "_of_" + suit + ".png";
            Image image = new Image(cardImage, "Card");
            image.setWidth("100px");
            image.getStyle().set("margin-left", "-80px");
            playerHandDealt.add(image);

            // remove one card from deck
            deck.remove(0);

            // update handSum value
            if (value.equals("jack") || value.equals("queen") || value.equals("king")) {
                handSum = handSum + 10;
            }
            else if (value.equals("ace")) {
                handSum = handSum + 11;
            }
            else {
                handSum = handSum + Integer.parseInt(value);
            }

            playerHandSumEntry.removeAll();
            H5 handSumTextNew = new H5("Player Hand Sum: " + handSum);
            playerHandSumEntry.add(handSumTextNew);

            // update counts
            if (value.equals("10") || value.equals("jack") || value.equals("queen") || value.equals("king")
                    || value.equals("ace")) {
                runCount--;
                trueCount = runCount / (deck.size() / 52);
            }
            else if (value.equals("2") || value.equals("3") || value.equals("4") || value.equals("5")
                    || value.equals("6")) {
                runCount++;
                trueCount = runCount / (deck.size() / 52);
            }
            else {
                trueCount = runCount / (deck.size() / 52);
            }

            // update counts text
            hLCountTexts.remove(runCountText, trueCountText);
            runCountText = new H4("Run Count: " + runCount);
            trueCountText = new H4("True Count: " + trueCount);
            hLCountTexts.add(runCountText, trueCountText);

            // did player get another ace
            if (value.equals("ace")) {
                numPlayerAces = numPlayerAces + 1;
            }

            // updates text to HLforSuggestion to show suggestion
            giveSuggestion();
            HLforSuggestion.removeAll();
            suggestionText = new H4(String.format("Suggested Action: %s", suggestionValue));
            HLforSuggestion.add(suggestionText);

            // checks if player busted but has an ace, makes ace = 1
            if (numPlayerAces > 0 && handSum > 21) {
                handSum = handSum - 10;
                numPlayerAces = numPlayerAces - 1;

                playerHandSumEntry.removeAll();
                handSumTextNew = new H5("Player Hand Sum: " + handSum);
                playerHandSumEntry.add(handSumTextNew);
            }

            // see if player busted
            // make sure we didn't split
            if (splitHandDealt.getComponentCount() == 0) {
                try {
                    bustOrNot();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            // check if we got BJ for when we hit after splitting
            if (handSum == 21 && playerHandDealt.getComponentCount() == 2) {
                BJ = true;

                // tells player to press next hand
                HLforSuggestion.removeAll();
                suggestionText = new H4(String.format("Suggested Action: %s", "Next Hand"));
                HLforSuggestion.add(suggestionText);
            }

        });

        // standButton clickListener
        standButton.addClickListener(event -> {

            // as long as we didn't split
            if (splitHandDealt.getComponentCount() == 0) {
                // remove face down card
                dealerHandDealt.remove(image4);

                // add cards to dealer
                dealerHits();
            }
        });

        // doubleButton clickListener
        doubleButton.addClickListener(event -> {
            // checks if playerHandDealt only has 2 cards
            if (playerHandDealt.getComponentCount() == 2) {

                // makes doubleOrNot true
                doubleOrNot = true;

                // add card to playerHandDealt
                String value = deck.get(0).getValue();
                String suit = deck.get(0).getSuit();

                String cardImage = "images/" + value + "_of_" + suit + ".png";
                Image image = new Image(cardImage, "Card");
                image.setWidth("100px");
                image.getStyle().set("margin-left", "-80px");
                playerHandDealt.add(image);

                // remove one card from deck
                deck.remove(0);

                // update handSum value
                if (value.equals("jack") || value.equals("queen") || value.equals("king")) {
                    handSum = handSum + 10;
                }
                else if (value.equals("ace")) {
                    handSum = handSum + 11;
                }
                else {
                    handSum = handSum + Integer.parseInt(value);
                }

                playerHandSumEntry.removeAll();
                H5 handSumTextNew = new H5("Player Hand Sum: " + handSum);
                playerHandSumEntry.add(handSumTextNew);

                // update counts
                if (value.equals("10") || value.equals("jack") || value.equals("queen") || value.equals("king")
                        || value.equals("ace")) {
                    runCount--;
                    trueCount = runCount / (deck.size() / 52);
                }
                else if (value.equals("2") || value.equals("3") || value.equals("4") || value.equals("5")
                        || value.equals("6")) {
                    runCount++;
                    trueCount = runCount / (deck.size() / 52);
                }
                else {
                    trueCount = runCount / (deck.size() / 52);
                }

                // update counts text
                hLCountTexts.remove(runCountText, trueCountText);
                runCountText = new H4("Run Count: " + runCount);
                trueCountText = new H4("True Count: " + trueCount);
                hLCountTexts.add(runCountText, trueCountText);

                // see if we got an ace
                if (value.equals("ace")) {
                    numPlayerAces = numPlayerAces + 1;
                }

                // checks if player busted but has an ace, makes ace = 1
                if (numPlayerAces > 0 && handSum > 21) {
                    handSum = handSum - 10;
                    numPlayerAces = numPlayerAces - 1;

                    playerHandSumEntry.removeAll();
                    handSumTextNew = new H5("Player Hand Sum: " + handSum);
                    playerHandSumEntry.add(handSumTextNew);
                }

                // see if player busted
                try {
                    bustOrNot();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                // deal dealer cards
                if (splitHandDealt.getComponentCount() == 0 && handSum < 22) {
                    // remove face down card
                    dealerHandDealt.remove(image4);

                    dealerHits();
                }
            }
        });

        // new action buttons for second hand from split
        Button hitSplitButton = new Button("Hit Second Hand");
        hitSplitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        Button standSplitButton = new Button("Stand Second Hand");
        standSplitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        Button doubleSplitButton = new Button("Double Second Hand");
        doubleSplitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);

        // splitButton clickListener
        splitButton.addClickListener(event -> {
            // make sure cards are the same and player only has 2 cards to split
            if (sameCard && playerHandDealt.getComponentCount() == 2) {

                // turn sameCard to false if split already
                sameCard = false;

                // add suggestion and outcome prediction text to HorizontalLayouts
                splitSuggestionText = new H5("Split Suggested Action: " + splitSuggestionValue);
                HLforSplitSuggestion.add(splitSuggestionText);
                splitOutcomePredictionText = new H5("Split Hand Outcome Prediction: " + splitPredictionValue);
                HLforSplitOutcomePrediction.add(splitOutcomePredictionText);

                // give suggestion on first hand and update text, have to hit
                HLforSuggestion.removeAll();
                suggestionText = new H4(String.format("Suggested Action: %s", "Hit"));
                HLforSuggestion.add(suggestionText);

                // give suggestion on split hand and update text
                giveSplitSuggestion();
                HLforSplitSuggestion.removeAll();
                splitSuggestionText = new H5(String.format("Split Suggested Action: %s", splitSuggestionValue));
                HLforSplitSuggestion.add(splitSuggestionText);

                // makes splitOrNot true
                splitOrNot = true;

                // add second card to split hand
                // resets alignment
                image3.getStyle().set("margin-left", "-0px");
                playerHandDealt.remove(image3);
                splitHandDealt.add(image3);

                // remove second card value from handSum and add to splitHandSum
                if (value3.equals("jack") || value3.equals("queen") || value3.equals("king")) {
                    handSum = handSum - 10;
                    splitHandSum = splitHandSum + 10;
                }
                else if (value3.equals("ace")) {
                    handSum = handSum - 11;
                    splitHandSum = splitHandSum + 11;
                }
                else {
                    handSum = handSum - Integer.parseInt(value3);
                    splitHandSum = splitHandSum + Integer.parseInt(value3);
                }

                playerHandSumEntry.removeAll();
                H5 handSumTextNew = new H5("Player Hand Sum: " + handSum);
                playerHandSumEntry.add(handSumTextNew);

                splitHandSumEntry.removeAll();
                H5 splitSumTextNew = new H5("Split Hand Sum: " + splitHandSum);
                splitHandSumEntry.add(splitSumTextNew);

                // add buttons to splitButtonsLayout
                splitButtonsLayout.add(hitSplitButton, standSplitButton, doubleSplitButton);

                // see if value3 is an ace, if so subtract playerAces by one and add to splitAces
                if (value3.equals("ace")) {
                    numPlayerAces = numPlayerAces - 1;
                    numSplitAces = numSplitAces + 1;
                }

            }
        });

        // click listeners for the second hand from split buttons
        hitSplitButton.addClickListener(event -> {

            // add card to playerHandDealt
            String value = deck.get(0).getValue();
            String suit = deck.get(0).getSuit();

            String cardImage = "images/" + value + "_of_" + suit + ".png";
            Image image = new Image(cardImage, "Card");
            image.setWidth("100px");
            image.getStyle().set("margin-left", "-80px");
            splitHandDealt.add(image);

            // remove one card from deck
            deck.remove(0);

            // update splitHandSum value
            if (value.equals("jack") || value.equals("queen") || value.equals("king")) {
                splitHandSum = splitHandSum + 10;
            }
            else if (value.equals("ace")) {
                splitHandSum = splitHandSum + 11;
            }
            else {
                splitHandSum = splitHandSum + Integer.parseInt(value);
            }

            splitHandSumEntry.removeAll();
            H5 splitSumTextNew = new H5("Split Hand Sum: " + splitHandSum);
            splitHandSumEntry.add(splitSumTextNew);

            // update counts
            if (value.equals("10") || value.equals("jack") || value.equals("queen") || value.equals("king")
                    || value.equals("ace")) {
                runCount--;
                trueCount = runCount / (deck.size() / 52);
            }
            else if (value.equals("2") || value.equals("3") || value.equals("4") || value.equals("5")
                    || value.equals("6")) {
                runCount++;
                trueCount = runCount / (deck.size() / 52);
            }
            else {
                trueCount = runCount / (deck.size() / 52);
            }

            // update counts text
            hLCountTexts.remove(runCountText, trueCountText);
            runCountText = new H4("Run Count: " + runCount);
            trueCountText = new H4("True Count: " + trueCount);
            hLCountTexts.add(runCountText, trueCountText);

            // check if ace was dealt
            if (value.equals("ace")) {
                numSplitAces = numSplitAces + 1;
            }

            // checks if player busted but has an ace, makes ace = 1
            if (numSplitAces > 0 && splitHandSum > 21) {
                splitHandSum = splitHandSum - 10;
                numSplitAces = numSplitAces - 1;

                splitHandSumEntry.removeAll();
                splitSumTextNew = new H5("Split Hand Sum: " + splitHandSum);
                splitHandSumEntry.add(splitSumTextNew);
            }

            // update win prediction if 2 cards are dealt to split hand
            if (splitHandDealt.getComponentCount() == 2) {
                // updates text to HLforSplitOutcomePrediction to show predicted outcome
                giveSplitOutcomePrediction();
                HLforSplitOutcomePrediction.removeAll();
                splitOutcomePredictionText = new H5(String.format("Split Hand Outcome Prediction: %s", splitPredictionValue));
                HLforSplitOutcomePrediction.add(splitOutcomePredictionText);
            }

            // check split BJ
            if (splitHandSum == 21 && splitHandDealt.getComponentCount() == 2) {
                splitBJ = true;

                // update text on split hand suggestion
                HLforSplitSuggestion.removeAll();
                splitSuggestionText = new H5(String.format("Split Suggested Action: %s", "Press Stand"));
                HLforSplitSuggestion.add(splitSuggestionText);
            }

            if (!splitBJ) {
                // give suggestion on split hand and update text
                giveSplitSuggestion();
                HLforSplitSuggestion.removeAll();
                splitSuggestionText = new H5(String.format("Split Suggested Action: %s", splitSuggestionValue));
                HLforSplitSuggestion.add(splitSuggestionText);
            }

            // see if player split hand busted
            // splitBustOrNot();
            try {
                bustOrNot();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        // standSplitButton clickListener
        standSplitButton.addClickListener(event -> {

            // remove face down card
            dealerHandDealt.remove(image4);

            // add cards to dealer
            dealerHits();

        });
        // doubleSplitButton clickListener
        doubleSplitButton.addClickListener(event -> {
            // checks if splitHandDealt only has 2 cards
            if (splitHandDealt.getComponentCount() == 2) {

                // makes splitDoubleOrNot true
                splitDoubleOrNot = true;

                // add card to splitHandDealt
                String value = deck.get(0).getValue();
                String suit = deck.get(0).getSuit();

                String cardImage = "images/" + value + "_of_" + suit + ".png";
                Image image = new Image(cardImage, "Card");
                image.setWidth("100px");
                image.getStyle().set("margin-left", "-80px");
                splitHandDealt.add(image);

                // remove one card from deck
                deck.remove(0);

                // update splitHandSum value
                if (value.equals("jack") || value.equals("queen") || value.equals("king")) {
                    splitHandSum = splitHandSum + 10;
                }
                else if (value.equals("ace")) {
                    splitHandSum = splitHandSum + 11;
                }
                else {
                    splitHandSum = splitHandSum + Integer.parseInt(value);
                }

                splitHandSumEntry.removeAll();
                H5 splitSumTextNew = new H5("Split Hand Sum: " + splitHandSum);
                splitHandSumEntry.add(splitSumTextNew);

                // update counts
                if (value.equals("10") || value.equals("jack") || value.equals("queen") || value.equals("king")
                        || value.equals("ace")) {
                    runCount--;
                    trueCount = runCount / (deck.size() / 52);
                }
                else if (value.equals("2") || value.equals("3") || value.equals("4") || value.equals("5")
                        || value.equals("6")) {
                    runCount++;
                    trueCount = runCount / (deck.size() / 52);
                }
                else {
                    trueCount = runCount / (deck.size() / 52);
                }

                // update counts text
                hLCountTexts.remove(runCountText, trueCountText);
                runCountText = new H4("Run Count: " + runCount);
                trueCountText = new H4("True Count: " + trueCount);
                hLCountTexts.add(runCountText, trueCountText);

                // check if ace was dealt
                if (value.equals("ace")) {
                    numSplitAces = numSplitAces + 1;
                }

                // checks if player busted but has an ace, makes ace = 1
                if (numSplitAces > 0 && splitHandSum > 21) {
                    splitHandSum = splitHandSum - 10;
                    numSplitAces = numSplitAces - 1;

                    splitHandSumEntry.removeAll();
                    splitSumTextNew = new H5("Split Hand Sum: " + splitHandSum);
                    splitHandSumEntry.add(splitSumTextNew);
                }

                // see if player split hand busted
                // splitBustOrNot();
                try {
                    bustOrNot();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                // remove face down card
                dealerHandDealt.remove(image4);

                // deal dealer cards
                dealerHits();
            }
        });

        // create next hand button

        // this would reset dealerUpCard, handSum, clear HorizontalLayouts with Cards, etc.
        // HorizontalLayout for nextHandButton
        HorizontalLayout hL4 = new HorizontalLayout();
        Button nextHandButton = new Button("Next Hand");
        nextHandButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        hL4.add(nextHandButton);
        add(hL4);
        // click listener for nextHandButton
        nextHandButton.addClickListener(event -> {

            // clear player and dealer hands from HorizontalLayouts
            playerHandDealt.removeAll(); dealerHandDealt.removeAll();

            // reset values
            handSum = 0; dealerUpCard = 0; dealerHandSum = 0;
            sameCard = false; value3 = ""; splitHandSum = 0;
            BJ = false; doubleOrNot = false; splitOrNot = false;
            splitBJ = false; splitDoubleOrNot = false; numSplitAces = 0;
            numPlayerAces = 0; numDealerAces = 0; suggestionValue = "";
            predictionValue = ""; splitSuggestionValue = ""; splitPredictionValue = "";

            // clear split hands from HorizontalLayouts
            splitHandDealt.removeAll(); splitButtonsLayout.removeAll();

            // reset the hand sum texts
            playerHandSumEntry.removeAll();
            H5 handSumTextNew = new H5("Player Hand Sum: " + handSum);
            playerHandSumEntry.add(handSumTextNew);

            dealerHandSumEntry.removeAll();
            H5 dealerHandSumTextNew = new H5("Dealer Hand Sum: " + dealerHandSum);
            dealerHandSumEntry.add(dealerHandSumTextNew);

            splitHandSumEntry.removeAll();
            // H5 splitSumTextNew = new H5("Split Hand Sum: " + splitHandSum);
            // splitHandSumEntry.add(splitSumTextNew);

            // update numHands
            numHands = numHands + 1;

            // updates text to HLforSuggestion to show suggestion
            HLforSuggestion.removeAll();
            suggestionText = new H4(String.format("Suggested Action: %s", suggestionValue));
            HLforSuggestion.add(suggestionText);

            // updates text to HLforOutcomePrediction to show predicted outcome
            HLforOutcomePrediction.removeAll();
            outcomePredictionText = new H4(String.format("Initial Hand Outcome Prediction: %s", predictionValue));
            HLforOutcomePrediction.add(outcomePredictionText);

            // resets split smile stuff
            HLforSplitSuggestion.removeAll(); HLforSplitOutcomePrediction.removeAll();

        });

        // implement the expectedBet

        // VerticalLayout to store everything
        VerticalLayout VLforExpectedBet = new VerticalLayout();
        add(VLforExpectedBet);

        // add header to section
        H2 expectedBetHeader = new H2("Bet Size Resources");
        VLforExpectedBet.add(expectedBetHeader);

        // IntegerField for number of hands wanted to forecast ahead
        IntegerField handsForecastedField = new IntegerField("Number of Hands to Forecast Ahead", "Enter Positive Integer");
        handsForecastedField.setMin(0);
        handsForecastedField.getElement().getStyle().set("width", "250px");
        VLforExpectedBet.add(handsForecastedField);

        // button to generate bet multiplier, recommended bet size, and bankroll after whatever number of hands inputted
        Button expectedBetButton = new Button("Calculate Recommended Bet Size");
        expectedBetButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        VLforExpectedBet.add(expectedBetButton);

        // HorizontalLayout to output info to
        VerticalLayout VLforExpectedBetOutput = new VerticalLayout();
        VLforExpectedBet.add(VLforExpectedBetOutput);

        // click listener for expectedBetButton
        expectedBetButton.addClickListener(event -> {

            // make sure there is a hand amount entered
            if (!handsForecastedField.isEmpty()) {

                // get needed values from fields
                int handsForcastedValue = handsForecastedField.getValue();
                int betValue = betAmtDropdown.getValue();

                // calculate values
                double betMultiplier = expectedBet.calcMultiplier(simulatedBetData, trueCount);
                double expectedBankroll = expectedBet.calcExpBank(simulatedBetData, betValue, handsForcastedValue, trueCount);
                int recommendedBetAmt = expectedBet.calcRecBet(simulatedBetData, trueCount, betValue);

                // print results
                VLforExpectedBetOutput.removeAll();
                unitMultiplierText = new H4("Recommended Bet Multiplier for Next " + handsForcastedValue + " hands: "
                        + betMultiplier + "x");
                recommendedBetText = new H4("Recommended Bet Size Using Multiplier for Next " +
                        handsForcastedValue + " hands: $" + recommendedBetAmt);
                expectedBankrollText = new H4(String.format("Expected Bankroll After %d hands: $%.2f",
                        handsForcastedValue, expectedBankroll));
                VLforExpectedBetOutput.add(unitMultiplierText, recommendedBetText, expectedBankrollText);
            }
        });

    }

    /**
     * Gives the outcome for the initial (first) hand
     */
    private void giveOutcomePrediction() {

        // Are we predicted to Win, Lose, or Push current hand?
        double winPredictionDouble = InitialPredictionOutcome.winPred(simulatedData, dealerHandSum, handSum);
        predictionValue = InitialPredictionOutcome.handResult(winPredictionDouble);

    }

    /**
     * Gives the outcome for the split (second) hand
     */
    private void giveSplitOutcomePrediction() {

        // Are we predicted to Win, Lose, or Push current hand?
        double splitWinPredictionDouble = InitialPredictionOutcome.winPred(simulatedData, dealerHandSum, splitHandSum);
        splitPredictionValue = InitialPredictionOutcome.handResult(splitWinPredictionDouble);

    }

    /**
     * Gives the user a suggestion of which action he or she should take on the initial (first) hand
     */
    private void giveSuggestion() {

        // call makeSuggestion() method and update suggestionValue
        suggestionValue = Suggestion.makeSuggestion(RF1, dealerHandSum, handSum, runCount, trueCount);

        // convert letters into words
        if (suggestionValue.equals("D")) {
            suggestionValue = "Double";
        }
        else if (suggestionValue.equals("H")) {
            suggestionValue = "Hit";
        }
        else {
            suggestionValue = "Stand";
        }

        // logic for double, can only double on third card
        if (playerHandDealt.getComponentCount() != 2 && suggestionValue.equals("Double")) {
            suggestionValue = "Hit";
        }

        // split logic
        if (playerHandDealt.getComponentCount() == 2) {
            if ((handSum == 4 || handSum == 6) && sameCard) {
                if (dealerHandSum == 2 || dealerHandSum == 3 || dealerHandSum == 4
                        || dealerHandSum == 5 || dealerHandSum == 6 || dealerHandSum == 7) {
                    suggestionValue = "Split";
                }
            }
            else if (handSum == 8 && sameCard) {
                if (dealerHandSum == 5 || dealerHandSum == 6) {
                    suggestionValue = "Split";
                }
            }
            else if (handSum == 12 && sameCard) {
                if (dealerHandSum == 2 || dealerHandSum == 3 || dealerHandSum == 4
                        || dealerHandSum == 5 || dealerHandSum == 6) {
                    suggestionValue = "Split";
                }
            }
            else if (handSum == 14 && sameCard) {
                if (dealerHandSum == 2 || dealerHandSum == 3 || dealerHandSum == 4
                        || dealerHandSum == 5 || dealerHandSum == 6 || dealerHandSum == 7) {
                    suggestionValue = "Split";
                }
            }
            else if ((handSum == 16 || handSum == 22 || numPlayerAces == 2) && sameCard) {
                suggestionValue = "Split";
            }
            else if (handSum == 18 && sameCard) {
                if (dealerHandSum == 2 || dealerHandSum == 3 || dealerHandSum == 4
                        || dealerHandSum == 5 || dealerHandSum == 6 || dealerHandSum == 8
                        || dealerHandSum == 9) {
                    suggestionValue = "Split";
                }
            }

        }
    }

    /**
     * Gives the user a suggestion of which action he or she should take on the split (second) hand
     */
    private void giveSplitSuggestion() {

        // call makeSuggestion() method and update suggestionValue
        splitSuggestionValue = Suggestion.makeSuggestion(RF1, dealerHandSum, splitHandSum, runCount, trueCount);

        // convert letters into words
        if (splitSuggestionValue.equals("D")) {
            splitSuggestionValue = "Double";
        }
        else if (splitSuggestionValue.equals("H")) {
            splitSuggestionValue = "Hit";
        }
        else {
            splitSuggestionValue = "Stand";
        }

        // logic for double, can only double on third card
        if (splitHandDealt.getComponentCount() != 2 && splitSuggestionValue.equals("Double")) {
            splitSuggestionValue = "Hit";
        }
    }

    /**
     * Reshuffles the deck of cards
     */
    private void reshuffleDeck() {

        // we can say average number of cards dealt per hand to dealer and player is 6
        if (numHands >= (6 * 52)/6) {
            // generate new deck
            deckGenerator();

            // reset counts
            trueCount = 0; runCount = 0;

            // update counts text
            hLCountTexts.remove(runCountText, trueCountText);
            runCountText = new H4("Run Count: " + runCount);
            trueCountText = new H4("True Count: " + trueCount);
            hLCountTexts.add(runCountText, trueCountText);

            // reset numHands
            numHands = 0;
        }
    }

    /**
     * Gives the result of the initial (first) hand
     */
    private void winLoseOrPush() {

        // win by blackjack
        if (BJ) {
            playerBalance = playerBalance + 1.5 * betAmount;

            hLPlayerBalance.remove(playerBalanceText);
            playerBalanceText = new H4(String.format("Balance: $%.2f", playerBalance));
            hLPlayerBalance.add(playerBalanceText);
        }
        // win by dealer bust or handSum > dealerHandSum while player not busting
        else if (dealerHandSum > 21 || (handSum < 22 && handSum > dealerHandSum)) {
            // checks if doubled
            if (doubleOrNot) {
                playerBalance = playerBalance + 2 * betAmount;
            }
            else {
                playerBalance = playerBalance + betAmount;
            }

            hLPlayerBalance.remove(playerBalanceText);
            playerBalanceText = new H4(String.format("Balance: $%.2f", playerBalance));
            hLPlayerBalance.add(playerBalanceText);
        }
        // lose by player bust and dealerHandSum > handSum without dealer busting
        else if (handSum > 21 || handSum < dealerHandSum) {
            // checks if doubled
            if (doubleOrNot) {
                playerBalance = playerBalance - 2 * betAmount;
            }
            else {
                playerBalance = playerBalance - betAmount;
            }

            hLPlayerBalance.remove(playerBalanceText);
            playerBalanceText = new H4(String.format("Balance: $%.2f", playerBalance));
            hLPlayerBalance.add(playerBalanceText);
        }
        // push if handSum = dealerHandSum
        // nothing changes if push
        else {
            hLPlayerBalance.remove(playerBalanceText);
            playerBalanceText = new H4(String.format("Balance: $%.2f", playerBalance));
            hLPlayerBalance.add(playerBalanceText);
        }

        // check if we need to reshuffle deck
        reshuffleDeck();
    }

    /**
     * Gives the result of the split (second) hand
     */
    private void splitWinLoseOrPush() {

        // win, loss, or push logic for split hand

        // win by blackjack
        if (splitBJ) {
            playerBalance = playerBalance + 1.5 * betAmount;

            hLPlayerBalance.remove(playerBalanceText);
            playerBalanceText = new H4(String.format("Balance: $%.2f", playerBalance));
            hLPlayerBalance.add(playerBalanceText);
        }
        // win by dealer bust or splitHandSum > dealerHandSum while player not busting
        else if (dealerHandSum > 21 || (splitHandSum < 22 && splitHandSum > dealerHandSum)) {
            // checks if doubled
            if (splitDoubleOrNot) {
                playerBalance = playerBalance + 2 * betAmount;
            }
            else {
                playerBalance = playerBalance + betAmount;
            }

            hLPlayerBalance.remove(playerBalanceText);
            playerBalanceText = new H4(String.format("Balance: $%.2f", playerBalance));
            hLPlayerBalance.add(playerBalanceText);
        }
        // lose by split player bust and dealerHandSum > splitHandSum without dealer busting
        else if (splitHandSum > 21 || splitHandSum < dealerHandSum) {
            // checks if doubled
            if (splitDoubleOrNot) {
                playerBalance = playerBalance - 2 * betAmount;
            }
            else {
                playerBalance = playerBalance - betAmount;
            }

            hLPlayerBalance.remove(playerBalanceText);
            playerBalanceText = new H4(String.format("Balance: $%.2f", playerBalance));
            hLPlayerBalance.add(playerBalanceText);
        }
        // push if handSum = dealerHandSum
        // nothing changes if push
        else {
            hLPlayerBalance.remove(playerBalanceText);
            playerBalanceText = new H4(String.format("Balance: $%.2f", playerBalance));
            hLPlayerBalance.add(playerBalanceText);
        }

        // win, loss, or push logic for original hand

        // win by blackjack
        if (BJ) {
            playerBalance = playerBalance + 1.5 * betAmount;

            hLPlayerBalance.remove(playerBalanceText);
            playerBalanceText = new H4(String.format("Balance: $%.2f", playerBalance));
            hLPlayerBalance.add(playerBalanceText);
        }
        // win by dealer bust or handSum > dealerHandSum while player not busting
        else if (dealerHandSum > 21 || (handSum < 22 && handSum > dealerHandSum)) {
            // checks if doubled
            if (doubleOrNot) {
                playerBalance = playerBalance + 2 * betAmount;
            }
            else {
                playerBalance = playerBalance + betAmount;
            }

            hLPlayerBalance.remove(playerBalanceText);
            playerBalanceText = new H4(String.format("Balance: $%.2f", playerBalance));
            hLPlayerBalance.add(playerBalanceText);
        }
        // lose by player bust and dealerHandSum > handSum without dealer busting
        else if (handSum > 21 || handSum < dealerHandSum) {
            // checks if doubled
            if (doubleOrNot) {
                playerBalance = playerBalance - 2 * betAmount;
            }
            else {
                playerBalance = playerBalance - betAmount;
            }

            hLPlayerBalance.remove(playerBalanceText);
            playerBalanceText = new H4(String.format("Balance: $%.2f", playerBalance));
            hLPlayerBalance.add(playerBalanceText);
        }
        // push if handSum = dealerHandSum
        // nothing changes if push
        else {
            hLPlayerBalance.remove(playerBalanceText);
            playerBalanceText = new H4(String.format("Balance: $%.2f", playerBalance));
            hLPlayerBalance.add(playerBalanceText);
        }

        // check if we need to reshuffle deck
        reshuffleDeck();
    }

    /**
     * Deals cards to dealer after player gets blackjack
     */
    private void playerBlackJack() {

        // add card to replace face down card
        // add card to dealerHandDealt
        String value = deck.get(0).getValue();
        String suit = deck.get(0).getSuit();

        String cardImage = "images/" + value + "_of_" + suit + ".png";
        Image image = new Image(cardImage, "Card");
        image.setWidth("100px");
        image.getStyle().set("margin-left", "-116px");
        dealerHandDealt.add(image);

        // update counts
        if (value.equals("10") || value.equals("jack") || value.equals("queen") || value.equals("king")
                || value.equals("ace")) {
            runCount--;
            trueCount = runCount / (deck.size() / 52);
        }
        else if (value.equals("2") || value.equals("3") || value.equals("4") || value.equals("5")
                || value.equals("6")) {
            runCount++;
            trueCount = runCount / (deck.size() / 52);
        }
        else {
            trueCount = runCount / (deck.size() / 52);
        }

        // update counts text
        hLCountTexts.remove(runCountText, trueCountText);
        runCountText = new H4("Run Count: " + runCount);
        trueCountText = new H4("True Count: " + trueCount);
        hLCountTexts.add(runCountText, trueCountText);

        // update dealerHandSum
        if (value.equals("jack") || value.equals("queen") || value.equals("king")) {
            dealerHandSum = dealerHandSum + 10;
        }
        else if (value.equals("ace")) {
            dealerHandSum = dealerHandSum + 11;
        }
        else {
            dealerHandSum = dealerHandSum + Integer.parseInt(value);
        }

        dealerHandSumEntry.removeAll();
        H5 dealerHandSumTextNew = new H5("Dealer Hand Sum: " + dealerHandSum);
        dealerHandSumEntry.add(dealerHandSumTextNew);

        // remove one card from deck
        deck.remove(0);

        if (!splitOrNot) {
            // checks if player won, lost, or pushed
            winLoseOrPush();
        }
        else {
            splitWinLoseOrPush();
        }
    }

    /**
     * Makes the decision whether the dealer busts or not
     * @throws InterruptedException accounts for the possibility of a specific error
     */
    private void bustOrNot() throws InterruptedException {

        // if we split and busted both hands
        if (splitOrNot && (handSum > 21 && splitHandSum > 21)) {
            // wait 2 second to execute
            // Thread.sleep(2000);

            // add cards to dealer
            // dealerHits();

            // update text on split hand suggestion
            HLforSplitSuggestion.removeAll();
            splitSuggestionText = new H5(String.format("Split Suggested Action: %s", "Next Hand"));
            HLforSplitSuggestion.add(splitSuggestionText);

            // add card to replace face down card
            // add card to dealerHandDealt
            String value = deck.get(0).getValue();
            String suit = deck.get(0).getSuit();

            String cardImage = "images/" + value + "_of_" + suit + ".png";
            Image image = new Image(cardImage, "Card");
            image.setWidth("100px");
            image.getStyle().set("margin-left", "-116px");
            dealerHandDealt.add(image);

            // update counts
            if (value.equals("10") || value.equals("jack") || value.equals("queen") || value.equals("king")
                    || value.equals("ace")) {
                runCount--;
                trueCount = runCount / (deck.size() / 52);
            }
            else if (value.equals("2") || value.equals("3") || value.equals("4") || value.equals("5")
                    || value.equals("6")) {
                runCount++;
                trueCount = runCount / (deck.size() / 52);
            }
            else {
                trueCount = runCount / (deck.size() / 52);
            }

            // update counts text
            hLCountTexts.remove(runCountText, trueCountText);
            runCountText = new H4("Run Count: " + runCount);
            trueCountText = new H4("True Count: " + trueCount);
            hLCountTexts.add(runCountText, trueCountText);

            // update dealerHandSum
            if (value.equals("jack") || value.equals("queen") || value.equals("king")) {
                dealerHandSum = dealerHandSum + 10;
            }
            else if (value.equals("ace")) {
                dealerHandSum = dealerHandSum + 11;
            }
            else {
                dealerHandSum = dealerHandSum + Integer.parseInt(value);
            }

            dealerHandSumEntry.removeAll();
            H5 dealerHandSumTextNew = new H5("Dealer Hand Sum: " + dealerHandSum);
            dealerHandSumEntry.add(dealerHandSumTextNew);

            // remove one card from deck
            deck.remove(0);

            if (!splitOrNot) {
                // checks if player won, lost, or pushed
                winLoseOrPush();
            }
            else {
                splitWinLoseOrPush();
            }
        }

        // if player's hand is > 21, they busted
        if (handSum > 21 && !splitOrNot) {

            // wait 2 second to execute
            // Thread.sleep(2000);

            // add cards to dealer
            // dealerHits();

            // tells player to press next hand
            HLforSuggestion.removeAll();
            suggestionText = new H4(String.format("Suggested Action: %s", "Next Hand"));
            HLforSuggestion.add(suggestionText);

            // add card to replace face down card
            // add card to dealerHandDealt
            String value = deck.get(0).getValue();
            String suit = deck.get(0).getSuit();

            String cardImage = "images/" + value + "_of_" + suit + ".png";
            Image image = new Image(cardImage, "Card");
            image.setWidth("100px");
            image.getStyle().set("margin-left", "-116px");
            dealerHandDealt.add(image);

            // update counts
            if (value.equals("10") || value.equals("jack") || value.equals("queen") || value.equals("king")
                    || value.equals("ace")) {
                runCount--;
                trueCount = runCount / (deck.size() / 52);
            }
            else if (value.equals("2") || value.equals("3") || value.equals("4") || value.equals("5")
                    || value.equals("6")) {
                runCount++;
                trueCount = runCount / (deck.size() / 52);
            }
            else {
                trueCount = runCount / (deck.size() / 52);
            }

            // update counts text
            hLCountTexts.remove(runCountText, trueCountText);
            runCountText = new H4("Run Count: " + runCount);
            trueCountText = new H4("True Count: " + trueCount);
            hLCountTexts.add(runCountText, trueCountText);

            // update dealerHandSum
            if (value.equals("jack") || value.equals("queen") || value.equals("king")) {
                dealerHandSum = dealerHandSum + 10;
            }
            else if (value.equals("ace")) {
                dealerHandSum = dealerHandSum + 11;
            }
            else {
                dealerHandSum = dealerHandSum + Integer.parseInt(value);
            }

            dealerHandSumEntry.removeAll();
            H5 dealerHandSumTextNew = new H5("Dealer Hand Sum: " + dealerHandSum);
            dealerHandSumEntry.add(dealerHandSumTextNew);

            // remove one card from deck
            deck.remove(0);

            if (!splitOrNot) {
                // checks if player won, lost, or pushed
                winLoseOrPush();
            }
            else {
                splitWinLoseOrPush();
            }

        }
    }

    /**
     * Makes the decision if the dealer hits
     */
    private void dealerHits() {

        // removes back of card image if still there
        if (dealerHandDealt.getComponentCount() == 2) {
            dealerHandDealt.remove(image4);
        }

        while (dealerHandSum < 17) {

            // wait 1 second to execute
            // Thread.sleep(1000);

            // add card to dealerHandDealt
            String value = deck.get(0).getValue();
            String suit = deck.get(0).getSuit();

            String cardImage = "images/" + value + "_of_" + suit + ".png";
            Image image = new Image(cardImage, "Card");
            image.setWidth("100px");
            image.getStyle().set("margin-left", "-80px");
            dealerHandDealt.add(image);

            // update dealerHandSum
            if (value.equals("jack") || value.equals("queen") || value.equals("king")) {
                dealerHandSum = dealerHandSum + 10;
            }
            else if (value.equals("ace")) {
                dealerHandSum = dealerHandSum + 11;
            }
            else {
                dealerHandSum = dealerHandSum + Integer.parseInt(value);
            }

            dealerHandSumEntry.removeAll();
            H5 dealerHandSumTextNew = new H5("Dealer Hand Sum: " + dealerHandSum);
            dealerHandSumEntry.add(dealerHandSumTextNew);

            // update counts
            if (value.equals("10") || value.equals("jack") || value.equals("queen") || value.equals("king")
                    || value.equals("ace")) {
                runCount--;
                trueCount = runCount / (deck.size() / 52);
            }
            else if (value.equals("2") || value.equals("3") || value.equals("4") || value.equals("5")
                    || value.equals("6")) {
                runCount++;
                trueCount = runCount / (deck.size() / 52);
            }
            else {
                trueCount = runCount / (deck.size() / 52);
            }

            // update counts text
            hLCountTexts.remove(runCountText, trueCountText);
            runCountText = new H4("Run Count: " + runCount);
            trueCountText = new H4("True Count: " + trueCount);
            hLCountTexts.add(runCountText, trueCountText);

            // remove one card from deck
            deck.remove(0);

            // see if dealer got an ace
            if (value.equals("ace")) {
                numDealerAces = numDealerAces + 1;
            }

            // checks if dealer busted but has an ace, makes ace = 1
            if (numDealerAces > 0 && dealerHandSum > 21) {
                dealerHandSum = dealerHandSum - 10;
                numDealerAces = numDealerAces - 1;

                dealerHandSumEntry.removeAll();
                dealerHandSumTextNew = new H5("Dealer Hand Sum: " + dealerHandSum);
                dealerHandSumEntry.add(dealerHandSumTextNew);
            }
        }

        // tells player to press next hand
        HLforSuggestion.removeAll();
        suggestionText = new H4(String.format("Suggested Action: %s", "Next Hand"));
        HLforSuggestion.add(suggestionText);

        // if player split, update suggestion to next hand
        if (splitOrNot) {
            // update text on split hand suggestion
            HLforSplitSuggestion.removeAll();
            splitSuggestionText = new H5(String.format("Split Suggested Action: %s", "Next Hand"));
            HLforSplitSuggestion.add(splitSuggestionText);
        }

        if (!splitOrNot) {
            // checks if player won, lost, or pushed
            winLoseOrPush();
        }
        else {
            splitWinLoseOrPush();
        }
    }

    /**
     * Generates the deck of cards to be used
     */
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

        // number of decks = 8
        for (int i = 0; i < 8; i++) {
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
