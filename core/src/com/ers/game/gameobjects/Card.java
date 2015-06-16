package com.ers.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.ers.game.helpers.AssetLoader;

/**
 * Created by clemus on 6/11/2015.
 */
public class Card {
    private final int suit,rank;
    private Sprite cardFaceUp;
    private Sprite cardFaceDown;


    private Sprite currFace;
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
        isFacedUp = false;
        currFace = new Sprite(AssetLoader.cardTextureAtlas.findRegion(ranks[rank] + "-" + suits[suit]));
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
        //currFace = new Sprite(AssetLoader.cardTextureAtlas.findRegion(ranks[rank] + "-" + suits[suit]));
        currFace = new Sprite(AssetLoader.faceDown);
        currFace.setPosition(x,y);
        currFace.setScale(5f);
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
    public int getRank(){ return rank; }

    public String getRankString(){return ranks[rank]; }
    /**
     * @return: int suit the suit
     */
    public int getSuit() { return suit; }
    public String getSuitsString(){return suits[suit]; }
    /**
     * True means the rank and suit are showing
     * False means the back of the card is showing
     * @return: boolean isFacedUp
     */
    public boolean getFacedUp() { return isFacedUp; }
    /**
     * Sets the card isFacedUp
     */
    public void setFacedUp(boolean isFacedUp) { this.isFacedUp = isFacedUp;  }

    public Sprite getCurrFace() {
        return currFace;
    }

    public void setCurrFace(Sprite currFace) {
        this.currFace = currFace;
    }

    public void onClick( ){
        if(isFacedUp){
            currFace.setRegion(AssetLoader.faceDown);
        }else{
            currFace.setRegion(AssetLoader.cardTextureAtlas.findRegion(getRankString() + "-" + getSuitsString()));
        }
    }
}// end card class
