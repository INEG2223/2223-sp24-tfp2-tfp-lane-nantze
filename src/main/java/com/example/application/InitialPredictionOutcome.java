package com.example.application;

import smile.data.formula.Formula;
import smile.regression.LinearModel;
import smile.regression.OLS;
import tech.tablesaw.api.*;

/**
 * Creates an initial prediction of the hand outcome given the player's hand and the dealer's up-card
 */
public class InitialPredictionOutcome {
    public static void main(String[] args) {

        Table simulatedData = readData();

        int playerInitial = 4; //Testing
        int dealerUpCard = 10; //Testing

        double winPrediction = winPred(simulatedData, dealerUpCard, playerInitial);

        String result = handResult(winPrediction);

        System.out.println("Prediction Outcome: " + result);
        System.out.println("Prediction Value: " + winPrediction);

    }

    /**
     * Calculates the hand prediction based upon the simulated hands in the dataset, player's initial hand, and the dealer's up card
     * @param simulatedData the table of simulated BlackJack hands
     * @param dealUpCard the dealer's first card while the second card is face down
     * @param playerInitial the player's initial hand (two cards)
     * @return The value of the hand prediction, (>0 = win, =0 = push, <0 = loss)
     */
    public static double winPred(Table simulatedData, int dealUpCard, int playerInitial) {

        //Creating a multiple linear regression model to predict the win column value based on player initial hand and dealer up card
        LinearModel winPred = OLS.fit(Formula.lhs("double_win"), simulatedData.selectColumns("double_win", "integer_dealer_up", "integer_initial_player_value").smile().toDataFrame());

        //Initialization
        double expectedWinOutcome = 0.0;

        //HAS TO BE IN THIS ORDER: dealer up card then playerInitialValue because of the order in winPred
        //Predicting value for the win column
        double[] initialHand = {dealUpCard, playerInitial};
        expectedWinOutcome = winPred.predict(initialHand);

        return expectedWinOutcome;

    }

    /**
     * Reads in a csv data file into a table and puts it in a usable format
     * @return A table that is in a usable format
     */
    public static Table readData() {
        Table simData = Table.read().csv("data/blackjack_random_sample_1M.csv");
        Table simulatedData = simData.copy();
        //Table simulatedData = simData;
        //System.out.println(testData);

        /*
        CHAT GPT CITE FOR REMOVING BRACES/COMMAS:
        https://chat.openai.com/share/e6d6ecef-0e1f-4b6f-9633-bf13b88d19b4
         */
        StringColumn playerFinalVal = simulatedData.stringColumn("player_final_value");
        StringColumn playerFinalValTrim = playerFinalVal.replaceAll("\\[", "");
        playerFinalValTrim = playerFinalValTrim.replaceAll("\\]", "");

        playerFinalValTrim.setName("player_final_value");
        simulatedData.replaceColumn("player_final_value", playerFinalValTrim);

        // Replacing Dealer BlackJack hands with a value of 21
        StringColumn DBJ = simulatedData.stringColumn("dealer_final_value");
        DBJ.replaceAll("BJ", "21");
        DBJ.setName("dealer_final_value");
        simulatedData.replaceColumn("dealer_final_value", DBJ);

        // Replacing Player BlackJack hands with a value of 21
        StringColumn PBJ = simulatedData.stringColumn("player_final_value");
        PBJ.replaceAll("'BJ'", "21");
        PBJ.setName("player_final_value");

        simulatedData.replaceColumn("player_final_value", PBJ);

        //Adding New Column to Calculate Player Initial Value
        StringColumn playerInitialVal;
        playerInitialVal = simulatedData.stringColumn("initial_hand").replaceAll("\\[", "");
        playerInitialVal = playerInitialVal.replaceAll("\\]", "");
        playerInitialVal.setName("player_initial_value");
        simulatedData.addColumns(playerInitialVal);

        IntColumn intInitialCPCol = IntColumn.create("integer_initial_player_value");

        for (String row : playerInitialVal) {
            String[] pIstringArray = row.split(", ");
            int pIVal0 = Integer.parseInt(pIstringArray[0]);
            int pIVal1 = Integer.parseInt(pIstringArray[1]);

            int newVal = pIVal0 + pIVal1;

            intInitialCPCol.append(newVal);
        }

        simulatedData.addColumns(intInitialCPCol);

        //System.out.println(simulatedData.sampleN(300));

        NumberColumn dealUp = (NumberColumn) simulatedData.numberColumn("dealer_up");
        NumberColumn intPlayer = (NumberColumn) simulatedData.numberColumn("integer_initial_player_value");
        NumberColumn win = (NumberColumn) simulatedData.numberColumn("win");

        //simulatedData.addColumns(dealUp, intPlayer, win);

        //Checking dealer_up column is an integer column
        IntColumn dealUppp = IntColumn.create("integer_dealer_up");
        for (Row row : simulatedData) {
            int dU = row.getInt("dealer_up");
            //String dU = row.getString("dealer_up");
            //int dealerUpVal = Integer.parseInt(dU);

            int dealerUpVal = dU;

            dealUppp.append(dealerUpVal);
        }
        simulatedData.addColumns(dealUppp);

        //Checking win column is a double column
        DoubleColumn winInteger = DoubleColumn.create("double_win");
        for (Row row : simulatedData) {
            double w = row.getDouble("win");
            //String w = row.getString("dealer_up");
            //int wVal = Integer.parseInt(dU);

            double wVal = w;

            winInteger.append(wVal);
        }

        simulatedData.addColumns(winInteger);

        //Creating a Boolean Column that states if the player won or not
        BooleanColumn playerWin = BooleanColumn.create("Player Win", win.isGreaterThanOrEqualTo(1.0), win.size());
        simulatedData.addColumns(playerWin);


        return simulatedData;
    }

    /**
     * Assigns the outcome of the hand based upon the prediction
     * @param winPrediction the numerical value of the prediction
     * @return The outcome of the hand ("Win", "Push", "Loss")
     */
    public static String handResult(double winPrediction){
        String result;

        if (winPrediction > 0){
            result = "Win";
        }
        else if (winPrediction == 0){
            result = "Push";
        }
        else {
            result = "Loss";
        }
        return result;
    }
}
