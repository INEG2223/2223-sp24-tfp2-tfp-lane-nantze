package com.example.application;

/**
 * Contains all necessary formatting that makes each Card have usable information coded into it
 */
public class Card {

    /**
     * Suit of the card
     */
    private String suit;
    /**
     * Numerical value of the card
     */
    private String value;

    /**
     * Gets the suit of the card
     * @return The suit of the card
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Sets the suit of the card to a specific suit
     * @param suit the specific suit of the card
     */
    public void setSuit(String suit) {
        this.suit = suit;
    }

    /**
     * Gets the value of the card
     * @return the value of the card
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the card to a specific value
     * @param value the numerical value of the card
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Creates a Card object that has a suit and value
     * @param suit the suit of the card
     * @param value the numerical value of the card
     */
    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    /**
     * Creates a default constructor for a blank card
     */
    public Card() {
        this.suit = "NoSuit";
        this.value = "NoValue";
    }

    /**
     * Generates an "ID" of the card that contains its suit & value
     * @return The "ID" of the card
     */
    @Override
    public String toString() {
        return "Card{" +
                "suit='" + suit + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
