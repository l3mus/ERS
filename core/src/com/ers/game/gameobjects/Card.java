package com.ers.game.gameobjects;

/**
 * Created by clemus on 6/11/2015.
 */
public class Card {
    private final int suit,rank;
    private float x,y;
    private boolean isFacedUp; //faced up mean showing the suit and rank shows
    private String[] suits = {"Spades","Hearts","Diamonds","Clubs"};
    private String[] ranks = {"Joker","Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"}; //Joker is normaly a suit but has been moved to a rank to allow "Ace" to start at index 1
    /**
     * Default Constructor
     * Sets suit to Spade
     * Sets rank to Ace
     * Sets the card faced down
     * Sets the x and y coordinates to 0, 0
     */
    public Card() {
        suit = 0;
        rank = 1;
        x = 0;
        y = 0;
        isFacedUp = false;
    }
    /**
     * Card Constructor
     *
     * @param: int suit
     *  The suit of the new card 0=Spades, 1=Hearts, 2=Diamonds, 3=Clubs
     * @param: int rank
     *  The suit of the new card 1=Ace, 2=2, 3=3, ... , 11=Jack, 12=Queen, 13=King
     */
    public Card(int suit, int rank, boolean isFacedUp,int x,int y){
        this.suit = suit;
        this.rank = rank;
        this.isFacedUp = isFacedUp;
        this.x = x;
        this.y = y;
    }
    /** to string the card info
     *
     * @return: the card name with the suit
     */
    @Override
    public String toString(){
        return ranks[rank] + " of " + suits[suit];
    }
    /**
     * @return: int rank the rank
     */
    public int getRank(){
        return rank;
    }
    /**
     * @return: int suit the suit
     */
    public int getSuit() { return suit; }
    /**
     * True means the rank and suit are showing
     * False means the back of the card is showing
     * @return: boolean isFacedUp
     */
    public boolean getFacedUp() { return isFacedUp; }
    /**
     * @return: float y the y coordinate
     */
    public float getX() { return x; }
    /**
     * @return: float y the y coordinate
     */
    public float getY() { return y; }
    /**
     * Sets the x coordinate
     */
    public void setX(float x) { this.x = x; }
    /**
     *  Sets the y coordinate
     */
    public void setY(float y) { this.y = y; }
    /**
     * Sets the card isFacedUp
     */
    public void setFacedUp(boolean isFacedUp) { this.isFacedUp = isFacedUp;  }

}// end card class
