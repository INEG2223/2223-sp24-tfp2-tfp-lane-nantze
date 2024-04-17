package com.example.application;

import java.util.ArrayList;

public class Game {

    private ArrayList<Card> deck;
    private final int numDecks = 20;
    private ArrayList<Card> playerCards;
    private ArrayList<Card> dealerCards;
    private ArrayList<Card> splitHand;

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public int getNumDecks() {
        return numDecks;
    }

    public ArrayList<Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(ArrayList<Card> playerCards) {
        this.playerCards = playerCards;
    }

    public ArrayList<Card> getDealerCards() {
        return dealerCards;
    }

    public void setDealerCards(ArrayList<Card> dealerCards) {
        this.dealerCards = dealerCards;
    }

    public ArrayList<Card> getSplitHand() {
        return splitHand;
    }

    public void setSplitHand(ArrayList<Card> splitHand) {
        this.splitHand = splitHand;
    }

    public Game(ArrayList<Card> deck, ArrayList<Card> playerCards, ArrayList<Card> dealerCards, ArrayList<Card> splitHand) {
        this.deck = deck;
        this.playerCards = playerCards;
        this.dealerCards = dealerCards;
        this.splitHand = splitHand;
    }

    public Game() {
        this.deck = new ArrayList<Card>();
        this.playerCards = new ArrayList<Card>();
        this.dealerCards = new ArrayList<Card>();
        this.splitHand = new ArrayList<Card>();
    }

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
