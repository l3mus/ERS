package com.ers.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.ers.game.gameobjects.Card;

import com.badlogic.gdx.math.MathUtils;

/**
 * Created by clemus on 6/11/2015.
 */
public class Deck {
    private Card[] deck; //Array of 52 cards, no jokers

    private int cardsUsed; //Keeps track of dealt cards

    /**
     * Constructs a deck of playing cards, the deck contains 52 cards.
     * Initially the cards are in a sorted order. The shuffle() method can
     * be called to randomize the order.
     */
    public Deck(){
        deck = new Card[52];
        int cardCount = 0; //How many cards have been created
        for( int suit = 0; suit <= 3; suit++) {
            for( int rank = 1; rank <= 13; rank++){
                deck[cardCount] = new Card(suit,rank,false, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
                cardCount++;
            }
        }


        cardsUsed = 0;
    }
    /**
     * Put all the used cards back into the deck, and
     * shuffle the deck in a random order
     */
    public void shuffle(){
        for( int i = deck.length-1; i >0; i--){
            int rand = (int)(MathUtils.random()*(i+1));
            Card temp = deck[i];
            deck[i]= deck[rand];
            deck[rand] = temp;
        }
        cardsUsed = 0;
    }

    /**
     * As cards are dealt from the deck, the number of cards left
     * decreases.
     *
     * @return number of cards left
     */
    public int cardsLeft(){
        return deck.length - cardsUsed;
    }
    /**
     * Removes the next card from the deck and return it. It
     * is illegal to call this method if there are no more cards in the deck.
     * You can check the number of cards remaining by calling the cardsLeft() function
     * @return the card which is removed from the deck
     * @throws java.lang.IllegalStateException ifthere are no cards left in the deck
     */
    public Card dealCard(){
        if(cardsUsed == deck.length)
            throw new IllegalStateException("No cards are left in the deck.");
        cardsUsed++;
        return deck[cardsUsed -1]; //Cards are not actually removed from deck
    }

}//end class Deck
