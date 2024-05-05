package com.example.application;

import smile.data.formula.Formula;
import smile.regression.LinearModel;
import smile.regression.OLS;
import tech.tablesaw.api.*;

/**
 * Calculates the recommended bet multiplier, recommended bet, and the expected bank balance of the user after a
 specified number of hands while using the recommended multiplier & bet.
 */
public class expectedBet {

    public static void main(String[] args) {

        Table simulatedData = readEData();

        int trueCount = 0; //NEED FROM VAADIN
        int betValue = 10; //NEED FROM VAADIN
        int hands = 10; //NEED FROM VAADIN

        double multiplier = calcMultiplier(simulatedData, trueCount);

        double winBet = calcExpBank(simulatedData, betValue, hands, trueCount);

        int recommendedBet = calcRecBet(simulatedData, trueCount, betValue);


        System.out.println("Recommended Current Hand Bet Multiplier: " + multiplier + "x");
        System.out.println("Recommended Current Hand Bet Using the Multiplier: $" + recommendedBet);

        System.out.print("Expected Bank Roll After " + hands + " hands: $");
        System.out.printf("%.2f",winBet);
        System.out.println();
    }

    /**
     * Calculates the recommended multiplier the user should multiply their bet by given the true count
     * @param simulatedData a table of simulated data used for prediciting the outcome of the hand based upon the true count
     * @param trueCount the true count of cards for the current game
     * @return A recommended multiplier the user should multiply their bet by
     */
    public static double calcMultiplier(Table simulatedData, int trueCount) {

        //Creating a multiple linear regression model to predict the win column value based on player initial hand and dealer up card
        LinearModel winPred = OLS.fit(Formula.lhs("double_win"), simulatedData.selectColumns("double_win", "true_count").smile().toDataFrame());

        //initializing
        double recommendendedMultiplyer = 0.0;

        //Prediciting the value of the win column given the current true count
        double[] trueC = {trueCount};
        double expectedWinOutcome = winPred.predict(trueC);

        //Giving a recommended multiplier based on the degree/tier of the win outcome prediction
        if (expectedWinOutcome < -1) {
            recommendendedMultiplyer = 0.025;
        } else if (expectedWinOutcome > -0.9999 && expectedWinOutcome < -0.9) {
            recommendendedMultiplyer = 0.05;
        } else if (expectedWinOutcome > -0.8999 && expectedWinOutcome < -0.8) {
            recommendendedMultiplyer = 0.15;
        } else if (expectedWinOutcome > -0.7999 && expectedWinOutcome < -0.7) {
            recommendendedMultiplyer = 0.25;
        } else if (expectedWinOutcome > -0.6999 && expectedWinOutcome < -0.6) {
            recommendendedMultiplyer = 0.35;
        } else if (expectedWinOutcome > -0.5999 && expectedWinOutcome < -0.5) {
            recommendendedMultiplyer = 0.45;
        } else if (expectedWinOutcome > -0.4999 && expectedWinOutcome < -0.4) {
            recommendendedMultiplyer = 0.55;
        } else if (expectedWinOutcome > -0.3999 && expectedWinOutcome < -0.3) {
            recommendendedMultiplyer = 0.65;
        } else if (expectedWinOutcome > -0.2999 && expectedWinOutcome < -0.2) {
            recommendendedMultiplyer = 0.75;
        } else if (expectedWinOutcome > -0.1999 && expectedWinOutcome < -0.1) {
            recommendendedMultiplyer = 0.85;
        } else if (expectedWinOutcome > -0.0999 && expectedWinOutcome < 0) {
            recommendendedMultiplyer = 0.95;
        } else if (expectedWinOutcome > 1) {
            recommendendedMultiplyer = 2;
        } else if (expectedWinOutcome < 0.9999 && expectedWinOutcome > 0.9) {
            recommendendedMultiplyer = 1.95;
        } else if (expectedWinOutcome < 0.8999 && expectedWinOutcome > 0.8) {
            recommendendedMultiplyer = 1.85;
        } else if (expectedWinOutcome < 0.7999 && expectedWinOutcome > 0.7) {
            recommendendedMultiplyer = 1.75;
        } else if (expectedWinOutcome < 0.6999 && expectedWinOutcome > 0.6) {
            recommendendedMultiplyer = 1.65;
        } else if (expectedWinOutcome < 0.5999 && expectedWinOutcome > 0.5) {
            recommendendedMultiplyer = 1.55;
        } else if (expectedWinOutcome < 0.4999 && expectedWinOutcome > 0.4) {
            recommendendedMultiplyer = 1.45;
        } else if (expectedWinOutcome < 0.3999 && expectedWinOutcome > 0.3) {
            recommendendedMultiplyer = 1.35;
        } else if (expectedWinOutcome < 0.2999 && expectedWinOutcome > 0.2) {
            recommendendedMultiplyer = 1.25;
        } else if (expectedWinOutcome < 0.1999 && expectedWinOutcome > 0.1) {
            recommendendedMultiplyer = 1.15;
        } else if (expectedWinOutcome < 0.0999 && expectedWinOutcome > 0) {
            recommendendedMultiplyer = 1.05;
        }

        return recommendendedMultiplyer;
    }

    /**
     * Calculates the recommended bet the user should place using the recommended multiplier
     * @param simulatedData a table used for predicting the outcome of the hand based upon the true count
     * @param trueCount the true count of cards for the current game
     * @param betValue the constant amount of money the user is betting for each hand
     * @return The recommended bet the user should place for the current hand
     */
    public static int calcRecBet(Table simulatedData, int trueCount, int betValue){
        //Creating a multiple linear regression model to predict the win column value based on player initial hand and dealer up card
        LinearModel winPred = OLS.fit(Formula.lhs("double_win"), simulatedData.selectColumns("double_win", "true_count").smile().toDataFrame());

        //initializing
        double recommendendedMultiplyer = 0.0;

        //Prediciting the value of the win column given the current true count
        double[] trueC = {trueCount};
        double expectedWinOutcome = winPred.predict(trueC);

        //Giving a recommended multiplier based on the degree/tier of the win outcome prediction
        if (expectedWinOutcome < -1) {
            recommendendedMultiplyer = 0.025;
        } else if (expectedWinOutcome > -0.9999 && expectedWinOutcome < -0.9) {
            recommendendedMultiplyer = 0.05;
        } else if (expectedWinOutcome > -0.8999 && expectedWinOutcome < -0.8) {
            recommendendedMultiplyer = 0.15;
        } else if (expectedWinOutcome > -0.7999 && expectedWinOutcome < -0.7) {
            recommendendedMultiplyer = 0.25;
        } else if (expectedWinOutcome > -0.6999 && expectedWinOutcome < -0.6) {
            recommendendedMultiplyer = 0.35;
        } else if (expectedWinOutcome > -0.5999 && expectedWinOutcome < -0.5) {
            recommendendedMultiplyer = 0.45;
        } else if (expectedWinOutcome > -0.4999 && expectedWinOutcome < -0.4) {
            recommendendedMultiplyer = 0.55;
        } else if (expectedWinOutcome > -0.3999 && expectedWinOutcome < -0.3) {
            recommendendedMultiplyer = 0.65;
        } else if (expectedWinOutcome > -0.2999 && expectedWinOutcome < -0.2) {
            recommendendedMultiplyer = 0.75;
        } else if (expectedWinOutcome > -0.1999 && expectedWinOutcome < -0.1) {
            recommendendedMultiplyer = 0.85;
        } else if (expectedWinOutcome > -0.0999 && expectedWinOutcome < 0) {
            recommendendedMultiplyer = 0.95;
        } else if (expectedWinOutcome > 1) {
            recommendendedMultiplyer = 2;
        } else if (expectedWinOutcome < 0.9999 && expectedWinOutcome > 0.9) {
            recommendendedMultiplyer = 1.95;
        } else if (expectedWinOutcome < 0.8999 && expectedWinOutcome > 0.8) {
            recommendendedMultiplyer = 1.85;
        } else if (expectedWinOutcome < 0.7999 && expectedWinOutcome > 0.7) {
            recommendendedMultiplyer = 1.75;
        } else if (expectedWinOutcome < 0.6999 && expectedWinOutcome > 0.6) {
            recommendendedMultiplyer = 1.65;
        } else if (expectedWinOutcome < 0.5999 && expectedWinOutcome > 0.5) {
            recommendendedMultiplyer = 1.55;
        } else if (expectedWinOutcome < 0.4999 && expectedWinOutcome > 0.4) {
            recommendendedMultiplyer = 1.45;
        } else if (expectedWinOutcome < 0.3999 && expectedWinOutcome > 0.3) {
            recommendendedMultiplyer = 1.35;
        } else if (expectedWinOutcome < 0.2999 && expectedWinOutcome > 0.2) {
            recommendendedMultiplyer = 1.25;
        } else if (expectedWinOutcome < 0.1999 && expectedWinOutcome > 0.1) {
            recommendendedMultiplyer = 1.15;
        } else if (expectedWinOutcome < 0.0999 && expectedWinOutcome > 0) {
            recommendendedMultiplyer = 1.05;
        }

        //Calculating Recommended bet
        double recBet = recommendendedMultiplyer * betValue;
        recBet = Math.round(recBet);

        return (int) recBet;
    }

    /**
     *
     * @param simulatedData a table used for predicting the outcome of the hand based upon the true count
     * @param betValue the constant amount of money the user is betting for each hand
     * @param hands the number of hands the user wishes to simulate using the recommended bet
     * @param trueCount the true count for the current shoe to be used when predicting bankroll
     * @return the simulated bank balance of the user based upon the true count and bet amount
     */
    public static double calcExpBank(Table simulatedData, double betValue, int hands, int trueCount){

        //Creating a multiple linear regression model to predict the win column value based on player initial hand and dealer up card
        LinearModel winPred = OLS.fit(Formula.lhs("double_win"), simulatedData.selectColumns("double_win", "true_count").smile().toDataFrame());


        //Initializing beginning bank balance and recommended multiplier
        double winBet = 0;
        double recommendendedMultiplyer = 0.0;

        //for-loop to calculate the expected bank balance over the specified amount of hands
        for (int i = 1; i <= hands; i++) {

            //Calculating a random value addition or subtraction for true count
            double coinFlip = Math.random();
            int sign;

            if (coinFlip < 0.5) {
                sign = -1;
            } else {
                sign = 1;
            }

            int newTrueCount = trueCount + sign;

            //Predicting the value of win column using the random true count
            double[] trueC = {newTrueCount};
            double expectedWinOutcome = winPred.predict(trueC);

            //Giving a recommended multiplier based on the degree/tier of the win outcome
            if (expectedWinOutcome < -1) {
                recommendendedMultiplyer = 0.025;
            } else if (expectedWinOutcome > -0.9999 && expectedWinOutcome < -0.9) {
                recommendendedMultiplyer = 0.05;
            } else if (expectedWinOutcome > -0.8999 && expectedWinOutcome < -0.8) {
                recommendendedMultiplyer = 0.15;
            } else if (expectedWinOutcome > -0.7999 && expectedWinOutcome < -0.7) {
                recommendendedMultiplyer = 0.25;
            } else if (expectedWinOutcome > -0.6999 && expectedWinOutcome < -0.6) {
                recommendendedMultiplyer = 0.35;
            } else if (expectedWinOutcome > -0.5999 && expectedWinOutcome < -0.5) {
                recommendendedMultiplyer = 0.45;
            } else if (expectedWinOutcome > -0.4999 && expectedWinOutcome < -0.4) {
                recommendendedMultiplyer = 0.55;
            } else if (expectedWinOutcome > -0.3999 && expectedWinOutcome < -0.3) {
                recommendendedMultiplyer = 0.65;
            } else if (expectedWinOutcome > -0.2999 && expectedWinOutcome < -0.2) {
                recommendendedMultiplyer = 0.75;
            } else if (expectedWinOutcome > -0.1999 && expectedWinOutcome < -0.1) {
                recommendendedMultiplyer = 0.85;
            } else if (expectedWinOutcome > -0.0999 && expectedWinOutcome < 0) {
                recommendendedMultiplyer = 0.95;
            } else if (expectedWinOutcome > 1) {
                recommendendedMultiplyer = 2;
            } else if (expectedWinOutcome < 0.9999 && expectedWinOutcome > 0.9) {
                recommendendedMultiplyer = 1.95;
            } else if (expectedWinOutcome < 0.8999 && expectedWinOutcome > 0.8) {
                recommendendedMultiplyer = 1.85;
            } else if (expectedWinOutcome < 0.7999 && expectedWinOutcome > 0.7) {
                recommendendedMultiplyer = 1.75;
            } else if (expectedWinOutcome < 0.6999 && expectedWinOutcome > 0.6) {
                recommendendedMultiplyer = 1.65;
            } else if (expectedWinOutcome < 0.5999 && expectedWinOutcome > 0.5) {
                recommendendedMultiplyer = 1.55;
            } else if (expectedWinOutcome < 0.4999 && expectedWinOutcome > 0.4) {
                recommendendedMultiplyer = 1.45;
            } else if (expectedWinOutcome < 0.3999 && expectedWinOutcome > 0.3) {
                recommendendedMultiplyer = 1.35;
            } else if (expectedWinOutcome < 0.2999 && expectedWinOutcome > 0.2) {
                recommendendedMultiplyer = 1.25;
            } else if (expectedWinOutcome < 0.1999 && expectedWinOutcome > 0.1) {
                recommendendedMultiplyer = 1.15;
            } else if (expectedWinOutcome < 0.0999 && expectedWinOutcome > 0) {
                recommendendedMultiplyer = 1.05;
            }

            //ensuring minimum bet
            if ((betValue * recommendendedMultiplyer) < 15) {
                betValue = 15;
            }

            //ensuring maximum bet
            if ((betValue * recommendendedMultiplyer) > 300){
                betValue = 300;
            }

            //Calculating the recommended bet size given the multiplier
            int newBet = (int) (betValue * recommendendedMultiplyer);

            //Calculating the effect on the bank balance
            if (expectedWinOutcome > 1.51) {

                winBet += 2 * newBet;

            } else if (expectedWinOutcome > 1.01 && expectedWinOutcome < 1.51) {

                winBet += 1.5 * newBet;

            } else if (expectedWinOutcome > 0 && expectedWinOutcome < 1.01) {

                winBet += 1 * newBet;

            } else if (expectedWinOutcome == 0.0) {

                winBet = newBet;

            } else if (expectedWinOutcome < 0 && expectedWinOutcome > -1.01) {

                winBet -= 1 * newBet;

            } else if (expectedWinOutcome < -1.01 && expectedWinOutcome > -1.51) {

                winBet -= 1.5 * newBet;

            } else {

                winBet -= 2 * newBet;

            }
        }
        return winBet;

    }

    /**
     * Reads a CSV data file into a table and formats it into a usable table
     * @return A table that is in a usable format
     */
    public static Table readEData() {

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

        //Check data and columns
        //System.out.println(simulatedData);
        return simulatedData;
    }

}
