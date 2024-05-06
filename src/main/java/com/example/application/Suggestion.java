package com.example.application;

import smile.base.cart.SplitRule;
import smile.classification.RandomForest;
import smile.data.Tuple;
import smile.data.formula.Formula;
import smile.data.type.DataTypes;
import smile.data.type.StructField;
import smile.data.type.StructType;
import smile.validation.metric.Accuracy;
import tech.tablesaw.api.*;

/**
 * Gives the user a suggested action (hit, stand, split, double down)
 */
public class Suggestion {

    public static void main(String[] args) {

        Table data = dataProcessing();

        RandomForest RFModel = randomForest(data);
        // should take care of splitting suggestion elsewhere
        // if not first action, change D to H

        System.out.println(makeSuggestion(RFModel, 3, 13, -4, 0));
    }

    /**
     * Processes the data and turns it into a table in a usable format
     * @return A table in a usable format
     */
    public static Table dataProcessing() {

        // =========================================
        // Data Preprocessing
        // =========================================

        // Here is the ChatGPT conversation to taking a random sample of the data: 1,000,000 rows
        // https://chat.openai.com/share/33e88380-ac01-44f9-9a28-4f225ab6235e

        // import csv using Tablesaw and create Table object
        // Table data = Table.read().csv("data/blackjack_simulator.csv");
        Table data = Table.read().csv("data/blackjack_random_sample_1M.csv");
        // System.out.println(data.first(1000));

        // deal with missing values appropriately
        // THIS DOESN'T REMOVE ANY COLUMNS, SO NO ROWS WITH MISSING DATA
        data = data.dropRowsWithMissingValues();
        // System.out.println(data.shape());

        // remove columns not needed at all
        data.removeColumns("shoe_id", "dealer_final");
        //System.out.println(data.first(3));

        // remove rows with "I" (Buy insurance), "R" (Surrender), and "P" (Split)
        // DEAL WITH SPLITS WITH OTHER LOGIC

        BooleanColumn containsBadActions = BooleanColumn.create("containsBadActions");
        StringColumn actions_taken = data.stringColumn("actions_taken");
        for (String row : actions_taken) {
            boolean removeOrNot = false;
            if (row.contains("I") || row.contains("R") || row.contains("P")) {
                removeOrNot = true;
            }
            containsBadActions.append(removeOrNot);
        }

        data.addColumns(containsBadActions);
        data = data.where(data.booleanColumn("containsBadActions").isFalse());
        data.removeColumns("containsBadActions");

        // remove brackets and ' ' and commas
        // get rid of the "N" in the actions_taken column, doesn't affect decision-making
        StringColumn actions_taken_cleaned = data.stringColumn("actions_taken").replaceAll("\\[","")
                .replaceAll("\\]","").replaceAll("'","")
                .replaceAll("N", "").replaceAll(",","");
        actions_taken_cleaned.setName("actions_taken");
        data.replaceColumn("actions_taken", actions_taken_cleaned);

        // remove any rows that have more than one action
        // replace all "H S" to "H"
        BooleanColumn containsTwoPlusActions = BooleanColumn.create("containsTwoPlusActions");
        StringColumn actions_taken2 = data.stringColumn("actions_taken").replaceAll("H S", "H");
        actions_taken2.setName("actions_taken");
        data.replaceColumn("actions_taken", actions_taken2);
        for (String row : actions_taken2) {
            boolean removeOrNot = false;
            if (row.contains(" ")) {
                removeOrNot = true;
            }
            containsTwoPlusActions.append(removeOrNot);
        }

        data.addColumns(containsTwoPlusActions);
        data = data.where(data.booleanColumn("containsTwoPlusActions").isFalse());
        data.removeColumns("containsTwoPlusActions");

        // System.out.println(data.first(1000));

        // clean initial_hand

        // remove brackets
        StringColumn initial_hand_wo_brackets = data.stringColumn("initial_hand").replaceAll("\\[", "")
                .replaceAll("\\]", "");
        initial_hand_wo_brackets.setName("initial_hand");
        data.replaceColumn("initial_hand",initial_hand_wo_brackets);
        //System.out.println(data.first(50));

        // add the two cards
        IntColumn playerInitialSum = IntColumn.create("playerInitialSum");

        for (String row : initial_hand_wo_brackets) {
            String[] twoCards = row.split(", ");
            int firstCard = Integer.parseInt(twoCards[0].trim());
            int secondCard = Integer.parseInt(twoCards[1].trim());

            int cardsSum = firstCard + secondCard;
            playerInitialSum.append(cardsSum);
        }
        playerInitialSum.setName("initial_hand_sum");
        data.replaceColumn("initial_hand", playerInitialSum);

        // System.out.println(data.first(50));

        // clean win column
        // change the "win" DoubleColumn to an IntColumn using -1,0,1

        DoubleColumn win = data.doubleColumn("win");
        IntColumn winV2 = IntColumn.create("win");
        for (double row : win) {
            if (row > 0) {
                winV2.append(1);
            }
            else if (row < 0) {
                winV2.append(-1);
            }
            else {
                winV2.append(0);
            }
        }
        data.replaceColumn("win", winV2);

        // System.out.println(data.first(50));

        // clean dealer_final_value
        // remove any rows where dealer gets BJ because no actions can be taken except insurance

        // CHECKING IF THERE EVEN ARE ANY BJ's
        // data = data.where(data.stringColumn("dealer_final_value").containsString("BJ"));
        // System.out.println(data.shape());

        BooleanColumn containsBJ = BooleanColumn.create("containsBJ");
        StringColumn dealer_final_value = data.stringColumn("dealer_final_value");
        for (String row : dealer_final_value) {
            boolean removeOrNot = false;
            if (row.contains("BJ")) {
                removeOrNot = true;
            }
            containsBJ.append(removeOrNot);
        }

        data.addColumns(containsBJ);
        data = data.where(data.booleanColumn("containsBJ").isFalse());
        data.removeColumns("containsBJ");

        // System.out.println(data.shape());
        // System.out.println(data.first(1000));

        // clean player_final_value

        // remove BJ
        BooleanColumn containsPlayerBJ = BooleanColumn.create("containsPlayerBJ");
        StringColumn player_final_value = data.stringColumn("player_final_value");
        for (String row : player_final_value) {
            boolean removeOrNot = false;
            if (row.contains("BJ")) {
                removeOrNot = true;
            }
            containsPlayerBJ.append(removeOrNot);
        }

        data.addColumns(containsPlayerBJ);
        data = data.where(data.booleanColumn("containsPlayerBJ").isFalse());
        data.removeColumns("containsPlayerBJ");

        // remove brackets
        StringColumn player_final_value_wo_brackets = data.stringColumn("player_final_value").replaceAll("\\[", "")
                .replaceAll("\\]", "");
        player_final_value_wo_brackets.setName("player_final_value");
        data.replaceColumn("player_final_value", player_final_value_wo_brackets);

        // System.out.println(data.first(1000));

        // clean and parse player_final

        // remove brackets and commas
        StringColumn player_final_wo_brackets = data.stringColumn("player_final").replaceAll("\\[", "")
                .replaceAll("\\]", "").replaceAll(",","");
        player_final_wo_brackets.setName("player_final");
        data.replaceColumn("player_final", player_final_wo_brackets);

        // remove first 2 cards
        StringColumn player_final_wo_first2cards = data.stringColumn("player_final")
                .replaceFirst("\\d+\\ \\d+", "").replaceAll(" ", ",")
                .replaceFirst(",", "").replaceAll(",", " ");
        player_final_wo_first2cards.setName("player_next_cards");
        data.replaceColumn("player_final", player_final_wo_first2cards);

        // System.out.println(data.first(10000));

        // parse player_next_cards and make a new columns with the new hand sum
        // couldn't do it

        // System.out.println(data.first(10000));

        // remove unusable columns
        data.removeColumns("cards_remaining", "dealer_final_value", "player_next_cards", "player_final_value");

        // System.out.println(data.first(10000));

        // filter so that we only analyze the hands that we won
        data = data.where(data.intColumn("win").isEqualTo(1));

        // System.out.println(data.first(1000));

        // remove win column because we won't know that beforehand
        data.removeColumns("win");

        // System.out.println(data.first(1000));

        // change name of "initial_hand_sum" to "hand_sum"
        IntColumn hand_sum = data.intColumn(data.column(1).name());
        hand_sum.setName("hand_sum");
        data.replaceColumn(data.column(1).name(), hand_sum);

        // System.out.println(data.column(1).name());

        // check to make sure columns are of the right type
        // System.out.println(data.structure());
        // System.out.println(data.shape());

        // change all actions to a number (D = 2, H = 1, S = 0)
        // also change "actions_taken" into an intColumn

        StringColumn actions_taken_as_ints = data.stringColumn("actions_taken");
        actions_taken_as_ints = actions_taken_as_ints.replaceAll("D", "2")
                .replaceAll("H", "1")
                .replaceAll("S", "0");
        IntColumn actions_taken_as_intC = actions_taken_as_ints.parseInt();
        actions_taken_as_intC.setName("actions_taken");
        data.replaceColumn("actions_taken", actions_taken_as_intC);

        // System.out.println(data.structure());

        // move actions_taken to end of table (right-most)
        IntColumn actions_taken_to_right = data.intColumn("actions_taken");
        data.removeColumns("actions_taken");
        data.addColumns(actions_taken_to_right);

        // System.out.println(data.first(10000));

        return data;
    }

    /**
     * Creates a RandomForest that can be used to give a suggested action
     * @param data the cleaned table to be used for predictions
     * @return A RandomForest that can be used to give a suggested action
     */
    public static RandomForest randomForest(Table data) {

        // =======================================
        // Build RandomForest Model to Output a Suggestion
        // =======================================

        // apply a train-test split method to assess the model's predictive accuracy with 30% test, 70% train
        Table[] splitData = data.sampleSplit(0.7);
        Table dataTrain = splitData[0];
        Table dataTest = splitData[1];

        // check to make sure 30% test, 70% train
        // System.out.println("Test Count: " + dataTest.rowCount());
        // System.out.println("Train Count: " + dataTrain.rowCount());

        //initial model with sensible parameters
        RandomForest RFModel1 = smile.classification.RandomForest.fit(
                Formula.lhs("actions_taken"),
                dataTrain.smile().toDataFrame(),
                50,
                (int) Math.sqrt((double) (dataTrain.columnCount() - 1)),
                SplitRule.GINI,
                7,
                100,
                5,
                1
        );

        // output a sample tree
        // System.out.println(RFModel1.trees()[0]);

        //predict the response of test dataset with RFModel1
        int[] predictions = RFModel1.predict(dataTest.smile().toDataFrame());

        //evaluate % classification accuracy for RFModel1
        double accuracy1 = Accuracy.of(dataTest.intColumn("actions_taken").asIntArray(), predictions);
        // System.out.println("Accuracy: " + accuracy1);

        //measure variable importance (mean decrease Gini Index)
        double[] RFModel1_Importance = RFModel1.importance();

        //plot variable importance with tablesaw
        /*
        Table varImportance = Table.create("featureImportance");
        List<String> featureNames = dataTrain.columnNames();
        featureNames.remove(4); //remove response
        varImportance.addColumns(DoubleColumn.create("featureImportance", RFModel1_Importance), StringColumn.create("Feature",  featureNames));
        varImportance = varImportance.sortDescendingOn("featureImportance");
        // Plot.show(HorizontalBarPlot.create("Feature Importance", varImportance, "Feature", "featureImportance"));

         */

        //construct correlation matrix, correlation between variables
        /*
        Table corr = Table.create("Spearman's Correlation Matrix");
        corr.addColumns(StringColumn.create("Feature"));
        for(String name: dataTest.removeColumns(2).columnNames())
        {
            corr.addColumns(DoubleColumn.create(name));
        }
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {
                corr.doubleColumn(i+1).append(dataTrain.numericColumns(i).get(0).asDoubleColumn().spearmans(dataTrain.numericColumns(j).get(0).asDoubleColumn()));
            }
        }
        corr.stringColumn("Feature").addAll(dataTrain.removeColumns(2).columnNames());

        for(Object ea: corr.columnsOfType(ColumnType.DOUBLE))
        {
            ((NumberColumn) ea).setPrintFormatter(NumberColumnFormatter.fixedWithGrouping(2));
        }

        System.out.println(corr.printAll());

         */

        return RFModel1;
    }

    /**
     * Gives the actual suggested action the user should take
     * @param RFModel1 the RandomForest used to determine the best action
     * @param dealer_up the dealer's shown card
     * @param hand_sum the numerical sum of the hand
     * @param run_count the run count of the deck
     * @param true_count the true count of the deck
     * @return The actual suggested action the user should take
     */
    public static String makeSuggestion(RandomForest RFModel1, int dealer_up, int hand_sum, int run_count, int true_count) {

        // create Tuple

        StructType schema = DataTypes.struct(
                new StructField("dealer_up", DataTypes.IntegerType),
                new StructField("hand_sum", DataTypes.IntegerType),
                new StructField("run_count", DataTypes.IntegerType),
                new StructField("true_count", DataTypes.IntegerType),
                new StructField("actions_taken", DataTypes.IntegerType)
        );

        int[] row = {dealer_up, hand_sum, run_count, true_count};

        Tuple tuple = Tuple.of(row, schema);

        // predict best suggestion
        int suggestion = RFModel1.predict(tuple);

        // D = 2, H = 1, S = 0
        if (suggestion == 2) {
            return "D";
        }
        else if (suggestion == 1) {
            return "H";
        }
        else {
            return "S";
        }
    }
}
