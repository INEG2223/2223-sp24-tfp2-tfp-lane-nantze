package com.example.application;

import java.util.ArrayList;

/**
 * Contains all necessary formatting in order for a proper game of Blackjack to be played
 */
public class Game {

    /**
     * A deck of cards
     */
    private ArrayList<Card> deck;

    /**
     * The total number of decks of cards
     */
    private final int numDecks = 20;

    /**
     * The player's cards (first hand)
     */
    private ArrayList<Card> playerCards;

    /**
     * The dealer's cards
     */
    private ArrayList<Card> dealerCards;

    /**
     * The player's split hand cards (second hand)
     */
    private ArrayList<Card> splitHand;

    /**
     * Gets a deck of cards
     * @return The current deck of cards
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Sets the deck of cards
     * @param deck a deck of cards
     */
    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    /**
     * Gets the total number of decks being used
     * @return the total number of decks
     */
    public int getNumDecks() {
        return numDecks;
    }

    /**
     * Gets the player's current cards
     * @return the player's current cards
     */
    public ArrayList<Card> getPlayerCards() {
        return playerCards;
    }

    /**
     * Sets the player's cards
     * @param playerCards the player's cards
     */
    public void setPlayerCards(ArrayList<Card> playerCards) {
        this.playerCards = playerCards;
    }

    /**
     * Gets the dealer's current cards
     * @return the dealer's current cards
     */
    public ArrayList<Card> getDealerCards() {
        return dealerCards;
    }

    /**
     * Sets the dealer's cards
     * @param dealerCards the dealer's cards
     */
    public void setDealerCards(ArrayList<Card> dealerCards) {
        this.dealerCards = dealerCards;
    }

    /**
     * Gets the player's split hand cards
     * @return the player's split hand cards
     */
    public ArrayList<Card> getSplitHand() {
        return splitHand;
    }

    /**
     * Sets the player's split hand cards
     * @return the player's split hand cards
     */
    public void setSplitHand(ArrayList<Card> splitHand) {
        this.splitHand = splitHand;
    }

    /**
     * Creates a game object that uses the deck, player's cards, and dealer's cards
     * @param deck the current deck of cards being used
     * @param playerCards the player's current cards
     * @param dealerCards the dealer's current cards
     * @param splitHand the player's split (second) hand current cards
     */
    public Game(ArrayList<Card> deck, ArrayList<Card> playerCards, ArrayList<Card> dealerCards, ArrayList<Card> splitHand) {
        this.deck = deck;
        this.playerCards = playerCards;
        this.dealerCards = dealerCards;
        this.splitHand = splitHand;
    }

    /**
     * Creates a blank game
     */
    public Game() {
        this.deck = new ArrayList<Card>();
        this.playerCards = new ArrayList<Card>();
        this.dealerCards = new ArrayList<Card>();
        this.splitHand = new ArrayList<Card>();
    }

    /**
     * Gives each game an "ID" that has specific information of each of the parameters
     * @return The game's "ID"
     */
    @Override
    public String toString() {
        return "Game{" +
                "deck=" + deck +
                ", numDecks=" + numDecks +
                ", playerCards=" + playerCards +
                ", dealerCards=" + dealerCards +
                ", splitHand=" + splitHand +
                '}';
    }
}
